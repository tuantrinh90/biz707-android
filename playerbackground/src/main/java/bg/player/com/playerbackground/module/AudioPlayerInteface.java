package bg.player.com.playerbackground.module;

public interface AudioPlayerInteface {
    void onComplete(boolean isReplay);

    void onNext();

    void onPrevious();

    void onDuration(long dur);

    void onEventTraining();

    void onSeek(long dur);
}
