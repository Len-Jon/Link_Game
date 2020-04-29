/*游戏面板*/
import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(){
        setTitle("LinkGame");//--------------------------------------------------------标题名称
        SA.WD = new WinDialog("LinkGame");//---------------------------------弹出对话框
        SA.Menu = new MenuPanel();//----------------------------------------------新建菜单框
        SA.GP = new GamePanel();//--------------------------------------------------新建游戏框
        SA.JSP = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT , SA.Menu,SA.GP );//新建竖直分隔栏以便添加到主窗体
        SA.JSP.setDividerLocation(240);

        add(SA.JSP);//添加分隔栏
        setBounds(300,25,1000,800);//设置大小
        setDefaultCloseOperation(EXIT_ON_CLOSE);//设置关闭
        validate();//刷新

        SA.WD.setVisible(true);//弹出窗口
        setVisible(true);//展示
    }
    public static void refresh (){//-----------------------------------------------------刷新
        SA.clock.stop();//--------------------------------------------------------------停下重置
        SA.seconds = 0;//-------------------------------------------------------------进度条数值重置
        SA.PB.setValue(0);//-----------------------------------------------------------进度条绘制重置
        SA.isPlaying = false;//---------------------------------------------------------游戏状态重置
        SA.GT = 0f;//------------------------------------------------------------------已用时间重置
        SA.showusedtime.setText(  String.format("%.1fs " , SA.GT)   );//显示时间重置
        SA.GF.remove(SA.JSP);//-------------------------------------------------------移除分隔栏组件
        SA.GP = new GamePanel();//-------------------------------------------------重建游戏面板
        SA.JSP = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT ,SA.Menu, SA.GP );//重组拆分窗格并添加//全局变量Menu板块不变
        SA.JSP.setDividerLocation(240);

        SA.GF.add(SA.JSP);

        SA.GF.validate();
        SA.GF.setVisible(true);//刷新使可见
    }
}
