package com.vishnus1224.minigithub.ui.presenter;

/**
 * Created by Vishnu on 2/6/2016.
 */
public interface Presenter {

    /**
     * Initialization for the presenter.
     */
    void init();

    /**
     * Resume operations in the presenter. Called in the onResume method of the concrete implementation.
     */
    void resume();

    /**
     * Pause operations in the presenter. Called in the onPause method of the concrete implementation.
     */
    void pause();

    /**
     * Release resources held by the presenter.
     */
    void destroy();
}
