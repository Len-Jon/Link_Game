/*游戏面板*/
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(){
        SA.button = new GameButton[SA.length][SA.length];//---------------------为按钮分配空间
        SA.initialize();//----------------------------------------------------------------初始化
        SA.renew();//------------------------------------------------------------------打乱顺序



        setLayout(new GridLayout(SA.length,SA.length));//-------------------设置布局循环控制添加按钮
        for(int i = 0 ; i < SA.length ; i++){
            for(int j = 0 ; j < SA.length ; j++){
                add(SA.button[i][j]);
            }
        }

        validate();
        setVisible(true);//刷新并显示
    }
    public static void reset(){//--------------------------------------重新排布
        for(int i = 0 ; i < SA.length ; i++){
            for(int j = 0 ; j < SA.length ; j++){
                SA.GP.add(SA.button[i][j]);
            }
        }
        SA.GP.validate();//多刷新保险一些
    }
}
