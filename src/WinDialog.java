/*弹出对话框*/
import javax.swing.*;
import java.awt.*;


public class WinDialog extends JDialog {
    JButton new_game = new JButton("新游戏");
    JLabel text;
    public WinDialog(String s){

        super(SA.GF , s , true);
        setLayout(new GridLayout(3,1));
        setBounds(700,200 , 100,120);//容器初始化

        text = new JLabel(s);//组件初始化
        text.setText(s);
        text.setFont(new Font("Microsoft YaHei" , Font.PLAIN,18));
        new_game.addActionListener(e -> {
                    setVisible(false);
                    if(!SA.isFirst) {//如果不是第一次运行,restart会报错，直接利用自动的新建即可
                        SA.restart();
                    }
                    else{
                        SA.isFirst = false;
                    }

                }
        );
        setLayout(new FlowLayout());//添加组件
        add(text );
        add(new_game );
        validate();
    }
}
