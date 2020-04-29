/*玩家数据*/
import java.io.Serializable;

public class Player implements Comparable , Serializable {
    public String name = "undefined";//默认undefined
    public float time;

    public Player(String name , float time){
        this.name = name;
        this.time = time;
    }
    public Player( float time){
        this.time = time;
    }

    @Override
    public int compareTo(Object o) {
        Player temp = (Player)o;

        if(this.time<temp.time || temp.time < 1){//由于读取是从小到大读取，因此与0相比要小于0
            return -1;
        }
        else{
            return 1;
        }
    }
}
