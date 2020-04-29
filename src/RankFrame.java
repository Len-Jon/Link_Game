/*排行榜窗口*/
import javax.swing.*;
import java.io.IOException;

public class RankFrame extends JFrame {
    public RankFrame() throws IOException, ClassNotFoundException {
        setBounds(0,0,300,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JTabbedPane p = new JTabbedPane(JTabbedPane.LEFT);

        for(int i = 0 ; i < 5 ; i++){
            p.add("Mode "+(i+1),new DataPanel(i));
        }//可能导致布局错乱

        add(p);

        validate();
        setVisible(true);
    }
}
