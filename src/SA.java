/*全局变量及方法，为什么叫SA,因为我随便打了两个字母……*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SA {//全局变量及方法
    public static boolean isFirst;//------------------是否为第一次运行
    public static int mode = 0; //-------------------游戏模式
    public static int rank = 9;//---------------------抽签
    public static int style = 0;//---------------------皮肤
    public static float GT = 0f;//--------------------游戏用时
    public static int delta = 30;           //-----------控制进度条单位时间增加
    public static boolean isPlaying = false;   //-----游戏状态
    public static int length = 8;//-------------------控制方块数量
    public static int seconds= 0;//------------------控制进度条数值

    public static GameButton[][] button;//---------方块地址
    public static GameButton temp1 = null;
    public static GameButton temp2 = null;//------检测用临时方块

    /*上到下依次为
    *开始弹窗
    * 主面板
    * 拆分窗格
    * 游戏面板  置于拆分窗格右边
    * 菜单面板  置于拆分窗格左边
    * 控制面板  置于菜单面板上边  控制刷新、重新开始和难度
    * 模式面板  置于控制面板  控制难度
    * 风格面板  置于控制面板  控制难度
    * 时间面板  置于菜单面板下边
    * 进度条     置于时间面板
    * 时间标签  置于时间面板显示已用时间*/
    public static WinDialog WD = null;
    public static GameFrame GF = null;
    public static JSplitPane JSP = null;
    public static GamePanel GP = null;
    public static MenuPanel Menu = null;
    public static ControlPanel CP = null;
    public static ModePanel MP = null;
    public static StylePanel SP= null;
    public static TimePanel TP = null;
    public static JProgressBar PB = null;
    public static JLabel showusedtime;

    public static Timer clock = null;//-----------------------------------------------计时器
    public static ActionListener count = new ActionListener() {//------------------计时器响应事件
        @Override
        public void actionPerformed(ActionEvent e) {
            SA.seconds+=delta;//------------------------------------------------------进度条数值增加
            SA.PB.setValue(SA.seconds);//---------------------------------------------进度条重绘
            GT += 0.1;//----------------------------------------------------------------单位时间设置的是100ms因此这里增加0.1s
            showusedtime.setText( String.format("%.1fs " , SA.GT)   );//显示当前时间标签
            if(SA.seconds >= 5000){//--------------------------------------------------如果进度条到顶游戏结束,重新开始,不记录成绩
                WD = new WinDialog("You Lose");
                WD.setVisible(true);
                restart();
            }
        }
    };//计时事件

    /*-----------直线检查-----------*/
    private static boolean test1(GameButton b1, GameButton b2){
        boolean check;
        int x1 = b1.x , y1 = b1.y;
        int x2 = b2.x , y2 = b2.y;
        int min , max;
        int i;
        if(x1 == x2 || y1 ==y2){//同行or同列
            if(x1 == x2){//同行
                min = Math.min(y1, y2);
                max = Math.max(y1, y2);

                if(max - min == 1){//相邻
                    check = true;
                }
                else{//不相邻
                    for(i = min + 1 ; i < max ; i++){//遍历之间的button，如有id不为0的打断，加不到max
                        if(button[x1][i].id != 0){
                            break;
                        }
                    }//如有id不为0的打断，加不到max
                    check = i == max;
                }
            }
            else{//同列，这一段把上一段复制一下改下坐标而已
                min = Math.min(x1, x2);
                max = Math.max(x1, x2);
                if(max - min == 1){
                    check = true;
                }
                else{
                    for(i = min + 1 ; i < max ; i++){
                        if(button[i][y1].id != 0){
                            break;
                        }
                    }
                    check = i == max;
                }
            }
        }
        else{
            check = false;
        }
        return check;
    }
     /*---------单拐点检查----------*/
    private static boolean test2(GameButton b1 , GameButton b2){//十字线会有两个焦点，必有一个是拐点，故两个if
        boolean check = false;
        //第一个拐点
        if(test1(b1 , button[b1.x][b2.y]) && (button[b1.x][b2.y].id == 0)){//拐点必须id==0
            if(test1(b2 , button[b1.x][b2.y])){
                check = true;
            }
        }
        //第二个拐点
        if(test1(b1 , button[b2.x][b1.y]) && (button[b2.x][b1.y].id == 0)){
            if(test1(b2 , button[b2.x][b1.y])){
                check = true;
            }
        }
        return check;
    }
    /*----------双拐点检查----------*/
    private static boolean test3(GameButton b1 , GameButton b2){//遍历两点十字上的点
        boolean check = false;
        int i ;
        int x , y;

        //行方向遍历
        for(i = 0 ; i < length ; i++){
            x = b1.x;
            y = i;

            if(button[x][y].id != 0){
                continue;
            }
            if(test1(b1 , button[x][y]) && test2(b2 , button[x][y])){
                check = true;
            }
        }
        //列方向遍历
        for( i = 0 ; i < length ; i++){
            x = i;
            y = b1.y;
            if(button[x][y].id != 0){
                continue;
            }
            if(test1(b1 , button[x][y]) && test2(b2 , button[x][y])){
                check = true;
            }
        }
        return check;
    }

    /*----------总检查---------------*/
    public static boolean test(GameButton b1 , GameButton b2){
        boolean check;
        if(b1.id != b2.id){
            check = false;
        }
        else check = test1(b1, b2) || test2(b1, b2) || test3(b1, b2);
        return check;
    }

    /*游戏面板初始化*/
    public static void initialize(){
        int l = length - 2;//l为可见部分边长
        int i , j;//临时下标

        for(i = 0 ; i < length - 1 ; i++){//边框无图片，不可见且id为0-----------------------------//从四个顶点分别同时出发可见部分边长-1
            button[0][i]                                = new GameButton(0 , 0 , i , null);//从左上角开始→
            button[i][length - 1]                   = new GameButton(0 , i , length - 1 , null);//从右上角开始↓
            button[length - 1][length - 1 - i] = new GameButton(0 , length-1 , length - 1 - i , null);//从右下角开始←
            button[length - 1 - i][0]              = new GameButton(0 , length - 1 - i , 0 , null);//从左下角开始↑
        }

        for(i = 1 ; i <= l/2 ; i++){
            for(j = 1 ; j <= l ; j++){

                int id = ((int) (Math.random() * 100)) % rank + 1 ;
                String path = "im"+style+"/" + id + ".gif";//随机分配id,并找到对应图片路径

                //这下面是必应搜索的...因为一旦打成jar包就读取不了图片了，所以上网搜了一下
                //这里原来是new ImageIcon(path) 的，只在编译器运行时有效
                ImageIcon im = new ImageIcon(  SA.class.getResource(path));
                button[i][j] = new GameButton(id , i , j  , im     );
                button[i][j].setBackground(new Color(0,0,0));
                button[i][j].setOpaque(false);//透明化
                button[i + l/2][j] = new GameButton(id ,i + l/2 , j  , im );
                button[i + l/2][j].setBackground(new Color(0,0,0));
                button[i + l/2][j].setOpaque(false);
                //保证两两对应
            }
        }
    }

    /*游戏面板刷新*/
    public static void renew(){
        int l = length - 2;//可见部分边长
        int i , x1 , y1 , x2 , y2;//临时下标
        GameButton temp;

        for(i = 1 ; i <= l*l ; i++) {    //随机交换 次数为元素总个数
            x1 = ((int) (Math.random() * 10)) % l + 1;
            y1 = ((int) (Math.random() * 10)) % l + 1;
            x2 = ((int) (Math.random() * 10)) % l + 1;
            y2 = ((int) (Math.random() * 10)) % l + 1;

            temp = button[x1][y1];
            button[x1][y1] = button[x2][y2];
            button[x2][y2] = temp;
            /*内参的游戏坐标也应该更换，保证于下标对应*/
            button[x1][y1].x=x1;
            button[x1][y1].y=y1;
            button[x2][y2].x=x2;
            button[x2][y2].y=y2;
        }
    }


    public static void restart(){//新游戏,直接调用GameFrame的方法，在这个类只是为了便于记忆
        GameFrame.refresh();
        GF.validate();
    }

    public static void win() throws IOException {//判断是否完成
        boolean check = true;
st:     for(int i = 0 ; i < length ; i++) {//遍历
            for(int j = 0 ; j < length ; j++){
                if(button[i][j].id != 0){
                    check = false;
                    break st;//只要有一个id不是0的直接false并退出
                }
            }
        }
        if(check){
            SA.clock.stop();
            SA.isPlaying = false;//停止计时器并修改游戏状态


            //记录成绩窗口 单位秒，保留一位小数；
            String player = JOptionPane.showInputDialog(GF,"请输入您的ID","恭喜！",JOptionPane.PLAIN_MESSAGE);//弹出对话框
            FileIO.mp[mode].add(new Player(player,GT));//添加进成绩TreeSet
            FileIO.mp[mode].pollLast();
            FileIO.Write();         //---------------------------------------写入
            WD = new WinDialog("You Win!"); //--------修改弹出窗口
            WD.setVisible(true);
            //弹出对话框，选择继续或退出
        }
    }

}
