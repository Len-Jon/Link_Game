/*控制面板*/
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class ControlPanel extends JPanel {
    JButton RENEW = new JButton("RENEW");
    JButton RESTART = new JButton("RESTART");
    JButton ranklist = new JButton("排行榜");
    public ControlPanel(){
        SA.MP = new ModePanel();//新建模式控制面板
        SA.SP = new StylePanel();//新建风格面板
        RENEW.addActionListener(e -> {
            SA.renew();//换位
            GamePanel.reset();//刷新输出
        });
        RESTART.addActionListener(e -> {
            SA.restart();
            //新游戏
        });//给两个按钮新增监听

        ranklist.addActionListener(e ->{
            try {
                new RankFrame();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });//--------------------------------------------------------------------------//调出排行榜窗体
        setLayout(new GridLayout(5,1));//设置纵向布局添加组件
        add(RENEW);
        add(RESTART);
        add(ranklist);
        add(SA.MP);
        add(SA.SP);

        validate();//老规矩，刷新一下总没错
    }
}
