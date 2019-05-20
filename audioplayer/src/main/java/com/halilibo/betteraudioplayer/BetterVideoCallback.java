package com.halilibo.betteraudioplayer;

/**
 * @author Aidan Follestad
 * Modified by Halil Ozercan
 */
public interface BetterVideoCallback {

    void onStarted(BetterVideoPlayer player);

    void onPaused(BetterVideoPlayer player);

    void onPreparing(BetterVideoPlayer player);

    void onPrepared(BetterVideoPlayer player);

    void onBuffering(int percent);

    void onError(BetterVideoPlayer player, Exception e);

    void onCompletion(BetterVideoPlayer player, boolean isReplay);

    void onToggleControls(BetterVideoPlayer player, boolean isShowing);

    void onFullScreen(boolean isFullScreen);

    void onNext();

    void onPrevious();
}