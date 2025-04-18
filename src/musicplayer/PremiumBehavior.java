package musicplayer;

public class PremiumBehavior implements UserBehavior{
    private int month = 0;

    public PremiumBehavior(int month) {
        setMonth(month);
    }

    public void setMonth(int month) {
        if (month <= 0){
            throw new IllegalArgumentException("Premium account time should not be a negative number");
        }
        this.month += month;
    }

    @Override
    public void createPlaylist(String title, User owner) throws InvalidOperationException {
        Playlist playlist = null;
        try {
            playlist = new Playlist(title, owner);
        } catch (Exception e) {
            throw new InvalidOperationException("Can not create playlist.");
        }
        Playlist.allPlaylists.add(playlist);
    }

    @Override
    public void playMusic(Music music){
        music.play();
    }

    @Override
    public void buyPremium(User owner, int month) throws InvalidOperationException {
        try {
            setMonth(month);
        } catch (IllegalArgumentException e) {
            throw new InvalidOperationException("length of premium account subscription is invalid.");
        }
    }

}