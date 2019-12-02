import java.io.*;
import java.util.*;

/** The leaderboard which saves the time and the name of the winner players
 * @author Bence
 *
 */
public class LeaderBoard {
    private ArrayList<Player> player_list;

    /** The constructor of the class
     */
    public LeaderBoard(){
        player_list = new ArrayList<Player>();
    }

    /** Size getter
     * @return Integer with the size of the list
     */
    public int getSize(){
        return player_list.size();
    }

    /** Adds a player to the list which is then ordered in ascending order by time
     * @param name String with the name of the player
     * @param time Integer with the time of the player in seconds
     */
    public void addPlayer(String name, int time){
        player_list.add(new Player(name,time));
        player_list.sort((o1, o2) -> o1.getTime() - o2.getTime());
    }

    /** Returns the name of the player with the given index
     * @param i Integer with the index of the player
     * @return	String with the name of the player
     */
    public String getName(int i){
        return  player_list.get(i).getName();
    }

    /** Returns the time of the player with the given index
     * @param i Integer with the index of the player
     * @return Integer with the time of the player
     */
    public int getTime(int i){
        return player_list.get(i).getTime();
    }

    /** Saves the list of players to a file
     * @throws IOException
     */
    public void saveToFile() throws IOException {
        FileOutputStream fo = new FileOutputStream("leaderboard");
        ObjectOutputStream out = new ObjectOutputStream(fo);
        out.writeObject(player_list);
        out.close();
    }

    /** Loads the list of players from a file
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	public  void loadFromFile() throws IOException {
        FileInputStream f = new FileInputStream("leaderboard");
        ObjectInputStream is = new ObjectInputStream(f);
        try {
            player_list = (ArrayList<Player>) is.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to read object.");
        }
        is.close();
    }

}
