package com.vishnus1224.minigithub.ui.activity;

import android.support.v7.app.AppCompatActivity;

import com.vishnus1224.minigithub.navigator.Navigator;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class BaseActivity extends AppCompatActivity {

    //Navigate between fragments and other activities using the singleton navigator.
    protected Navigator navigator = Navigator.INSTANCE;

}
