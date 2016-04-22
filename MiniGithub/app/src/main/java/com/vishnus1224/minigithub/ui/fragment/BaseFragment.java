package com.vishnus1224.minigithub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishnus1224.minigithub.MiniGithub;
import com.vishnus1224.minigithub.di.component.NetComponent;

/**
 * Created by Vishnu on 2/6/2016.
 */
public abstract class BaseFragment extends Fragment {

    public abstract void fetchData(String searchKeyword);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * Get the net component from the application class.
     * @return The net component initialized in application class.
     */
    public NetComponent getNetComponent(){
        return getApplication().getNetComponent();
    }

    /**
     * Get application instance.
     * @return The application instance cast to MiniGithub.
     */
    public MiniGithub getApplication(){
        return (MiniGithub) getActivity().getApplication();
    }
}
