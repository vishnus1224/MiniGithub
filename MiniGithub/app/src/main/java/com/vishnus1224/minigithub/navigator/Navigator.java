package com.vishnus1224.minigithub.navigator;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;


/**
 * Created by Vishnu on 2/6/2016.
 */
public enum  Navigator {

    INSTANCE;

    public void navigateToActivity(Context context, Class activityClass, Parcelable parcel, String parcelKey){

        Intent intent = new Intent(context, activityClass);
        intent.putExtra(parcelKey, parcel);
        context.startActivity(intent);

    }

}
