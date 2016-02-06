package com.vishnus1224.minigithub.generator;

/**
 * Helper class for generating a new instance of the specified fragment.
 * Created by Vishnu on 2/6/2016.
 */
public class FragmentGenerator {

    //Generates a new fragment instance of the input class type.
    public static <T> T generateFragment(Class<T> fragmentClass){

        try {
            return fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
