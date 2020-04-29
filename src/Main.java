/*程序入口*/
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SA.isFirst = true;//声明第一次运行
        new FileIO();//建立读写
        SA.GF = new GameFrame();//建立主窗口
    }
}
