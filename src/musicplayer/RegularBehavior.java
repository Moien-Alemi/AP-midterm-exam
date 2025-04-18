package musicplayer;

public class RegularBehavior implements UserBehavior{
    int playingLimit = 5;

    @Override
    public void createPlaylist(String Title, User Owner) throws InvalidOperationException {
        throw new InvalidOperationException("Only premium users can create playlists.");
    }

    @Override
    public void playMusic(Music music) throws InvalidOperationException {
        if(playingLimit == 0){
            throw new InvalidOperationException("Cannot play music\nYou should buy premium account.");
        }
        playingLimit--;
        music.play();
    }

    @Override
    public void buyPremium(User owner, int month) throws InvalidOperationException {
        try {
            owner.setBehavior(new PremiumBehavior(month));
        } catch (IllegalArgumentException e) {
            throw new InvalidOperationException("length of premium account subscription is invalid.");
        }
    }
}
