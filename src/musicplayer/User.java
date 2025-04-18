package musicplayer;

import java.util.ArrayList;

public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();

    private String username;
    private String password;
    private UserBehavior behavior;
    private ArrayList<Playlist> playlists;
    private ArrayList<User> followingList;
    private ArrayList<User> followerList;

    public User(String username, String password) throws InvalidOperationException {
        if(username == null || username.equals("")){
            throw new IllegalArgumentException("Username is invalid");
        }
        if(password.length() < 8){
            throw new InvalidOperationException("Password is invalid");
        }
        for (User user : allUsers){
            if(user.username.equals(username)){
                throw new InvalidOperationException("This username already exists.");
            }
        }
        this.username = username;
        this.password = password;
        this.behavior = new RegularBehavior();
        allUsers.add(this);
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void follow (User user) throws InvalidOperationException {
        for(User u : allUsers){
            if(u.username.equals(user.username)){
                followingList.add(user);
                user.followerList.add(this);
                return;
            }
        }
        throw new InvalidOperationException("This user doesn't exist.");
    }

    public void createPlaylist (String title) throws InvalidOperationException{
        this.behavior.createPlaylist(title, this);
    }

    public void playMusic (Music music){
        this.behavior.playMusic(music);
    }

    public void buyPremium (int month) throws InvalidOperationException {
        this.behavior.buyPremium(this, month);
    }
}