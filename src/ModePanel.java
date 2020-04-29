/*模式控制*/
/*如增加模式,排行榜也要增加*/
import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class ModePanel extends JPanel {
    JComboBox<String> list;//难度选项
    public ModePanel(){
        list = new JComboBox<>();
        list.addItem("Easy Mode");
        list.addItem("Middle Mode");
        list.addItem("Hard Mode");
        list.addItem("Hell Mode");
        list.addItem("? ? ?");
        add(list, BorderLayout.CENTER);
        validate();//-------------------------------------------------------------------添加难度选项刷新

        list.addItemListener(e -> {//事件监听
            String s = (Objects.requireNonNull(list.getSelectedItem())).toString();
            switch (s){
                case "Easy Mode":
                    SA.mode = 0;
                    SA.length = 8;
                    SA.delta = 30;
                    SA.rank = 9;
                    SA.style = 0;
                    SA.SP.setVisible(true);
                    SA.restart();
                break;
                case "Middle Mode":
                    SA.mode = 1;
                    SA.length = 12;
                    SA.delta = 32;
                    SA.rank = 9;
                    SA.style = 0;
                    SA.SP.setVisible(true);
                    SA.restart();
                break;
                case "Hard Mode":
                    SA.mode = 2;
                    SA.length = 16;
                    SA.delta = 37;
                    SA.rank = 9;
                    SA.style = 0;
                    SA.SP.setVisible(true);
                    SA.restart();
                break;
                case "Hell Mode":
                    SA.mode = 3;
                    SA.length = 18;
                    SA.delta = 42;
                    SA.rank = 9;
                    SA.style = 0;
                    SA.SP.setVisible(true);
                    SA.restart();
                break;
                case "? ? ?":
                    SA.mode = 3;
                    SA.length = 18;
                    SA.delta = 42;
                    SA.rank = 27;
                    SA.style = 4;
                    SA.SP.setVisible(false);
                    SA.restart();
                break;
            }
        });
    }
}
