package com.vishnus1224.minigithub.listener;

import android.widget.ImageView;

/** Listener for getting callback necessary for loading images.
 * Created by Vishnu on 2/13/2016.
 */
public interface ImageLoaderListener {

    void loadImage(ImageView imageView, String imageURL);
}
