import java.io.Serializable;

/** The class which stores the player's data
 * @author Bence
 *
 */
@SuppressWarnings("serial")
public class Player implements Serializable {
    private  String name;
    private int time;

    /** The constructor of the class
     * @param name String with the name of the player
     * @param time Integer with the time of the player in seconds
     */
    public Player(String name, int time){
        this.name = name;
        this.time = time;
    }

    /** Returns the name of the player
     * @return String with the player's name
     */
    public String getName(){
        return name;
    }

    /** Returns the time of the player
     * @return Integer with the player's time
     */
    public int getTime(){
        return  time;
    }
}
