package org.sfeto.nongli;

import android.view.View;

import org.hamcrest.Matcher;

/**
 * Created by qinghao on 16-11-14.
 */

public class EspressoTestsMatchers {

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }
}