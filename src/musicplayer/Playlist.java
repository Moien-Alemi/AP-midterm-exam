package musicplayer;

import java.util.ArrayList;
import java.util.Random;

public class Playlist {
    public static ArrayList<Playlist> allPlaylists = new ArrayList<>();

    private String title;
    private User owner;
    private ArrayList<Music> playlist;

    public Playlist(String title, User owner) {
        if(title == null || title.equals("")){
            throw new IllegalArgumentException("Title is invalid.");
        }if(owner == null){
            throw new IllegalArgumentException("Owner is invalid.");
        }
        this.title = title;
        this.owner = owner;
        playlist = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public User getOwner() {
        return owner;
    }

    public void editTitle(String password, String newTitle) throws InvalidOperationException {
        checkPassword(password);

        if(newTitle == null || newTitle.equals("")){
            throw new IllegalArgumentException("Title is invalid.");
        }

        title = newTitle;
    }

    public void addMusic(String password, Music newMusic) throws InvalidOperationException {
        checkPassword(password);

        if(newMusic == null){
            throw new IllegalArgumentException("Music is invalid.");
        }

        Music music = this.search(newMusic.getTitle(), newMusic.getSinger());
        if(music != null)
            throw new InvalidOperationException("This music already exists in playlist.");

        playlist.add(newMusic);
    }

    public void removeMusic(String password, Music music) throws InvalidOperationException {
        checkPassword(password);

        if(music == null){
            throw new IllegalArgumentException("Music is invalid.");
        }

        Music m = this.search(music.getTitle(), music.getSinger());
        if(m == null)
            throw new InvalidOperationException("This music doesn't exists in playlist.");

        playlist.remove(m);
    }


    public void playPlaylist() throws InvalidOperationException {
        if(playlist.isEmpty())
            throw new InvalidOperationException("play list is empty.");
        String descriptions = "";
        int counter = 1;
        for (Music music : playlist){
            System.out.print(counter + ": ");
            music.play();
            System.out.println();
        }
    }

    public void shuffle() throws InvalidOperationException {
        Random r = new Random();
        Music temp;
        for (int i=playlist.size()-1; i>0; i--){
            int j = r.nextInt(i);

            temp = playlist.get(i);
            playlist.set(i, playlist.get(j));
            playlist.set(j, temp);
        }
        playPlaylist();
    }

    public ArrayList<Music> search(String musicName){
        ArrayList<Music> foundMusics = new ArrayList<>();
        for (Music music : playlist){
            if(music.getTitle().equals(musicName)){
                foundMusics.add(music);
            }
        }
        if(foundMusics.isEmpty())
            return null;
        return foundMusics;
    }

    public Music search(String musicName, User singer){
        for (Music music : this.playlist){
            if(music.getTitle().equals(musicName) && music.getSinger().getUsername().equals(singer.getUsername())){
                return music;
            }
        }
        return null;
    }

    private void checkPassword(String password) throws InvalidOperationException {
        if(!(password.equals(owner.getPassword()))){
            throw new InvalidOperationException("password is incorrect.");
        }
    }
}
