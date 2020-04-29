/*成绩的数据面板*/
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DataPanel extends JPanel {
    public DataPanel(int mode) throws IOException, ClassNotFoundException {

        FileIO.Read();//---------------------------------------------------------------读出数据
        setLayout(new GridLayout(6,2));//----------------------------前五名和标题一共6行2列


        add(new JLabel("玩家",JLabel.CENTER));
        add(new JLabel("时间",JLabel.CENTER));
        
        for(Player temp : FileIO.mp[mode]){//增强for循环直接读取
            add(new JLabel("  "+temp.name , JLabel.CENTER) );
            String name = String.format("%.1f" , temp.time);
            add(new JLabel(name , JLabel.CENTER));
        }
        validate();
    }
}
