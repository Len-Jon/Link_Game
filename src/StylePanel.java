/*风格控制*/
import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class StylePanel extends JPanel {
    JComboBox<String> list;//难度选项
    public StylePanel(){
        list = new JComboBox<>();
        list.addItem("默认风格");
        list.addItem("麻将-万");
        list.addItem("麻将-条");
        list.addItem("麻将-筒");
       // list.addItem("麻将-混合");
        add(list, BorderLayout.CENTER);
        validate();//-------------------------------------------------------------------添加风格选项刷新

        list.addItemListener(e -> {//事件监听
            String s = (Objects.requireNonNull(list.getSelectedItem())).toString();
            switch (s){
                case "默认风格":
                    SA.style = 0;
                    SA.restart();
                    break;
                case "麻将-万":
                    SA.style = 1;
                    SA.restart();
                break;
                case "麻将-条":
                    SA.style = 2;
                    SA.restart();
                break;
                case "麻将-筒":
                    SA.style = 3;
                    SA.restart();
                break;
            }
        });
    }
}
