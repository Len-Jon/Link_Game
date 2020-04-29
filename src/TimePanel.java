/*时间面板*/
import javax.swing.*;
import java.awt.*;

public class TimePanel extends JPanel {
    public TimePanel(){
        SA.showusedtime = new JLabel("",JLabel.CENTER);//显示时间的标签
        SA.showusedtime.setFont(new Font("Microsoft YaHei" , Font.PLAIN,18));//设置格式好看些
        SA.showusedtime.setText(   String.format("%.1fs " , SA.GT)  );//取一位小数
        SA.PB = new JProgressBar(JProgressBar.HORIZONTAL,1,5000);//设置进度条单位长度1，总值5000，方向水平

        setLayout(new GridLayout(6,1));//预留位置使这两个组件紧凑些
        add(SA.PB);
        add(SA.showusedtime);

        SA.clock = new Timer(100 , SA.count);//设置计时器单位时间100ms
        validate();
    }
}
