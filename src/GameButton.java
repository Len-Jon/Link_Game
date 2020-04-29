/*游戏方块*/
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameButton extends JButton {
    public int x; //行
    public int y; //列
    public int id;//图片标记

    ActionListener click = e -> {//按钮监听点击事件
        if(!SA.isPlaying){
            SA.isPlaying = true;
            SA.clock.restart();//重启计时器
        }//点击方块开始游戏
        if(SA.temp1 == null){
            SA.temp1 = (GameButton)e.getSource();
        }//将点击的第一个方块存储到temp1
        else if(SA.temp2 == null) {
            if (    ! (  e.getSource().equals(SA.temp1) ) )//排除连续点击同方块
                SA.temp2 = (GameButton) e.getSource();//采集temp2
            else {
                return;                                                   //优化体验，点击同一个方块保持temp1不变
            }

            if(SA.test(SA.temp1,SA.temp2)){//匹配成功则进行消除：

                SA.button[SA.temp1.x][SA.temp1.y].setVisible(false);
                SA.button[SA.temp1.x][SA.temp1.y].id = 0;
                SA.button[SA.temp2.x][SA.temp2.y].setVisible(false);
                SA.button[SA.temp2.x][SA.temp2.y].id = 0;      //id清空并设置不可见

                if(SA.seconds>=500)
                    SA.seconds -=500;
                else
                    SA.seconds = 0;//-----------------------------进度条数值减少,不足100则清零

                SA.temp1 = null;
                try {
                    SA.win();//深度搜索，判断游戏是否完成
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else{//优化体验，不匹配temp2变temp1,temp2清空
                SA.temp1 = SA.temp2;
            }
            SA.temp2 = null;
        }
        SA.GP.validate();
        SA.GF.validate();//刷新总没错
    };

    public GameButton(int id , int x , int y , Icon icon){
        super(icon);//调用父类构造方法安插图片
        this.x = x;
        this.y = y;
        this.id = id;
        setBorder(null);
        setVisible(true);
        if(id == 0){
            setVisible(false);//边框方块设置不可见
        }
        else{
            addActionListener(click);//添加点击事件监听
        }
    }
}
