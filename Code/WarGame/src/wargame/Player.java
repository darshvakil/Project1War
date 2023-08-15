package wargame;

import java.io.Serializable;
/**
 *
 * @author shrey
 */
public class Player implements Serializable {
    private String username;
    private String password;
    private int score;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.score = 0;
    }

    // Getters and setters for username, password, and score

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore() {
        score++;
    }

    @Override
    public String toString() {
        return "Player: " + username + " | Score: " + score;
    }
}