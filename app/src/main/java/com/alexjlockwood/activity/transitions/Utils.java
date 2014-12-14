package com.alexjlockwood.activity.transitions;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.Iterator;
import java.util.Set;

public final class Utils {

    public static final int[] RADIOHEAD_ALBUM_IDS = {
            R.drawable.pablo_honey, R.drawable.the_bends,
            R.drawable.ok_computer, R.drawable.kid_a,
            R.drawable.amnesiac, R.drawable.hail_to_the_thief,
            R.drawable.in_rainbows, R.drawable.the_king_of_limbs,
    };

    public static final String[] RADIOHEAD_ALBUM_NAMES = {
            "Pablo Honey", "The Bends", "OK Computer", "Kid A",
            "Amnesiac", "Hail to the Thief", "In Rainbows", "The King of Limbs",
    };

    public static final int[] RADIOHEAD_BACKGROUND_IDS = {
            R.drawable.jonny_drums, R.drawable.thom2,
            R.drawable.thom_yell, R.drawable.thom3,
            R.drawable.thom4, R.drawable.thom5,
            R.drawable.jonny_thom, R.drawable.thom6,
    };

    public static final String[] RADIOHEAD_ALBUM_URLS = {
            "http://en.wikipedia.org/wiki/Pablo_Honey",
            "http://en.wikipedia.org/wiki/The_Bends",
            "http://en.wikipedia.org/wiki/OK_Computer",
            "http://en.wikipedia.org/wiki/Kid_A",
            "http://en.wikipedia.org/wiki/Amnesiac_(album)",
            "http://en.wikipedia.org/wiki/Hail_to_the_Thief",
            "http://en.wikipedia.org/wiki/In_Rainbows",
            "http://en.wikipedia.org/wiki/The_King_of_Limbs",
    };

    /**
     * Returns true if {@param view} is contained within {@param container}'s bounds.
     */
    public static boolean isViewInBounds(@NonNull View container, @NonNull View view) {
        Rect containerBounds = new Rect();
        container.getHitRect(containerBounds);
        return view.getLocalVisibleRect(containerBounds);
    }

    /**
     * Returns a string representation of {@param set}. Used only for debugging purposes.
     */
    @NonNull
    public static String setToString(@NonNull Set<String> set) {
        Iterator<String> i = set.iterator();
        if (!i.hasNext()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append('[');
        while (true) {
            sb.append(i.next());
            if (!i.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(", ");
        }
    }

    private Utils() {
        // This utility class should not be instantiated.
    }
}