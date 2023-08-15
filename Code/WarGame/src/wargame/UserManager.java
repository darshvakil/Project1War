
/**
 *
 * @author shrey
 */

package wargame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UserManager {
    private List<Player> players;
    private static final String DATA_FILE = "D:\\Sheridan\\JAVA\\week8\\WarGame\\src\\wargame";

    public UserManager() {
        players = new ArrayList<>();
        loadPlayers();
    }
    
    private void loadPlayers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            players = (List<Player>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions
        }
    }

    private void savePlayers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(players);
        } catch (IOException e) {
            // Handle exceptions
        }
    }

    public boolean registerPlayer(String username, String password) {
        if (!isUsernameTaken(username)) {
            players.add(new Player(username, password));
            savePlayers();
            return true;
        }
        return false;
    }

    public Player login(String username, String password) {
        for (Player player : players) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password)) {
                return player;
            }
        }
        return null;
    }

    private boolean isUsernameTaken(String username) {
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}