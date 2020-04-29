/*排行榜文件读取*/
import java.io.*;
import java.util.TreeSet;

public class FileIO {
    public static TreeSet<Player> [] mp;//四个模式的数据总地址
    //以下为文件读写相关
    public static File fl ;
    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;

    public FileIO() throws IOException, ClassNotFoundException {
        mp = new TreeSet[5] ;//单个模式初始化
        //fl = new File(String.valueOf(FileIO.class.getResource("rank.dat")) );
        fl = new File("rank2.0.dat" );

        if(!fl.exists()){//如果数据文件不存在，初始化并以赋值
            fl.createNewFile();

            fos = new FileOutputStream(fl);
            oos = new ObjectOutputStream(fos);

            for(int i = 0 ; i < 5 ; i++){
                mp[i] = new TreeSet<>();
                for(int j = 0 ; j < 5 ; j++){
                    mp[i].add(new Player(0));
                }
            }
            oos.writeObject(mp);//初始化完成并写入
            oos.flush();
            oos.close();
            fos.close();
        }
        else{
            Read();
        }
    }
/*读*/
    public static void Read() throws IOException, ClassNotFoundException {
        fis = new FileInputStream(fl);
        ois = new ObjectInputStream(fis);
        mp = (TreeSet<Player>[]) ois.readObject();
        ois.close();
        fis.close();
    }
/*写*/
    public static void Write() throws IOException {
        fos = new FileOutputStream(fl);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(mp);
        oos.flush();
        oos.close();
        fos.close();
    }
}
