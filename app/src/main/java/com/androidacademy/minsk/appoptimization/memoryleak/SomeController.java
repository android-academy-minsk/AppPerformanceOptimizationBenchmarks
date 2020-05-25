package com.androidacademy.minsk.appoptimization.memoryleak;

import android.app.Activity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * java class. it's important for memory leak test
 */
public class SomeController {
    private List<SomeListener> listeners = new ArrayList<>();

    public void registerActivity(Activity activity) {
        listeners.add(new SomeBadListener(activity));
        listeners.add(new SomeListener() { //test anonymous class
            @Override
            public void onEvent(@NotNull Object event) {
                //refer to some activity method/ field.
                // it's important for memory test cause it will leads to generate synthetic constructor
                if (!activity.isFinishing()) {
                    //do something
                }

            }
        });

        listeners.add(event -> { //test lambda
            activity.isFinishing(); //refer to some activity method/ field.
        });


        listeners.add(new SomeListener() { //test anonymous class
            @Override
            public void onEvent(@NotNull Object event) {
                //no-op
            }
        });
    }

    public void registerListener(SomeListener listener) {
        listeners.add(listener);

        listeners.add(new SomeListener() { //test anonymous class
            @Override
            public void onEvent(@NotNull Object event) {
                int a = 0; //do something
            }
        });
    }
}
