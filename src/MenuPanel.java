/*菜单面板*/
import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(){
        SA.CP = new ControlPanel();//新建控制面板
        SA.TP = new TimePanel();//新建计时面板

        setLayout(new GridLayout(3,1));//纵向添加
        add(SA.CP);
        add(SA.TP);
        add(new JLabel(new ImageIcon(SA.class.getResource("im0/touxiang.png"))));//皮一下，把我头像加上
        validate();//刷新
    }
}
