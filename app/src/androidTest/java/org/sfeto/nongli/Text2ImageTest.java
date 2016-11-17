package org.sfeto.nongli;


import android.app.Activity;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.sfeto.nongli.EspressoTestsMatchers.withDrawable;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Text2ImageTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void testGetIconTest() {
        Activity activity = mActivityTestRule.getActivity();
        //activity.setContentView();
        ViewInteraction title = getTitle();
        title.check(matches(withText(R.string.app_name)));
        ViewInteraction img_nongli = onView(withId(R.id.img_nongli));
        img_nongli.check(matches(withDrawable(R.drawable.nongli_icon)));

        ViewInteraction updateImg = onView(
                allOf(withId(R.id.updateImg)));
        updateImg.perform(click());

        title = getTitle();
        title.check(matches(withText("updateImg_onclick")));
        img_nongli = onView(withId(R.id.img_nongli));
        img_nongli.check(matches(not(withDrawable(R.drawable.nongli_icon))));
    }

    private ViewInteraction getTitle() {
        return onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.action_bar),
                                childAtPosition(
                                        withId(R.id.action_bar_container),
                                        0)),
                        0),
                        isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
