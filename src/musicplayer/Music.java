package musicplayer;

import java.util.ArrayList;

public class Music {
    private static ArrayList<Music> allMusics = new ArrayList<>();

    private String title;
    private User singer;
    private int numberOfStream = 0;

    public Music(String title, User singer) throws InvalidOperationException {
        if (title == null || title.equals("")) {
            throw new IllegalArgumentException("Title is invalid.");
        }
        if (singer == null) {
            throw new IllegalArgumentException("Singer is invalid.");
        }
        Music music = search(title, singer);
        if(music != null)
            throw new InvalidOperationException("This music already exists in the app.");
        this.title = title;
        this.singer = singer;
        allMusics.add(this);
    }

    public String getTitle() {
        return title;
    }

    public User getSinger() {
        return singer;
    }

    public void play(){
        numberOfStream++;
        System.out.println("Music name: " + title + ",\tSinger name: " + singer.getUsername() + ",\tnumber of streams: " + numberOfStream);
    }

    public static ArrayList<Music> search(String musicName){
        ArrayList<Music> foundMusics = new ArrayList<>();
        for (Music music : allMusics){
            if(music.title.equals(musicName)){
                foundMusics.add(music);
            }
        }
        if(foundMusics.isEmpty())
            return null;
        return foundMusics;
    }

    public static Music search(String musicName, User singer){
        for (Music music : allMusics){
            if(music.title.equals(musicName) && music.singer.getUsername().equals(singer.getUsername())){
               return music;
            }
        }
        return null;
    }
}
