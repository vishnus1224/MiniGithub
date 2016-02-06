package com.vishnus1224.minigithub.ui.presenter;

import com.vishnus1224.minigithub.ui.view.BaseView;

/**
 * Created by Vishnu on 2/6/2016.
 */
public interface Presenter {

    /**
     * Initialization for the presenter.
     */
    void init(BaseView view);

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
