package com.openclassrooms.entrevoisins.ui.neighbour_list;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule =
            new ActivityTestRule<>(ListNeighbourActivity.class);

    /**
     * first, check if favorite list is containing 0 element
     * then, add 2 element in this list
     * check if the list is now containing 2 elements
     * then remove an element from this list
     * finally check if the list is now containing 1 element
     * */
    @Test
    public void listNeighbourActivityTest() {
        // click on favorites tab
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(childAtPosition(withId(R.id.tabs),0),1),
                        isDisplayed()));
        tabView.perform(click());

        // move to favorites tab
        ViewInteraction viewPager = onView(
                allOf(withId(R.id.container),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(withId(android.R.id.content),0)),1),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        // check if list_neighbours is diplayed in the container
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),withParent(allOf(withId(R.id.container),withParent(withId(R.id.main_content)))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
        // and contains 0 element:
        recyclerView.check(withItemCount(0));

        // click on my neighbours tab
        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("My Neighbours"),
                        childAtPosition(childAtPosition(withId(R.id.tabs),0),0),
                        isDisplayed()));
        tabView2.perform(click());
        // move to my neighbours tab
        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.container),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(withId(android.R.id.content),0)),1),
                        isDisplayed()));
        viewPager2.perform(swipeRight());

        // Add an element to favorite list
        // click on first neighbour to see detail
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.item_list_avatar),
                        childAtPosition(childAtPosition(withId(R.id.list_neighbours),0),0),
                        isDisplayed()));
        appCompatImageView.perform(click());
        // click on add favorite button
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.button_add_favorite),
                        childAtPosition(childAtPosition(withId(android.R.id.content),0),3),
                        isDisplayed()));
        floatingActionButton.perform(click());
        // come back to neighbour list:
        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.button_previous_page),
                        childAtPosition(childAtPosition(withId(android.R.id.content),0),4),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        // Add an element to favorite list
        // click on third neighbour to see detail
        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.item_list_avatar),
                        childAtPosition(childAtPosition(withId(R.id.list_neighbours),2),0),
                        isDisplayed()));
        appCompatImageView2.perform(click());
        // click on add favorite button
        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.button_add_favorite),
                        childAtPosition(childAtPosition(withId(android.R.id.content),0),3),
                        isDisplayed()));
        floatingActionButton3.perform(click());
        // come back to neighbour list:
        ViewInteraction floatingActionButton4 = onView(
                allOf(withId(R.id.button_previous_page),
                        childAtPosition(childAtPosition(withId(android.R.id.content),0),4),
                        isDisplayed()));
        floatingActionButton4.perform(click());

        // click on favorites tab
        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(childAtPosition(withId(R.id.tabs),0),1),
                        isDisplayed()));
        tabView3.perform(click());
        // move to favorites tab
        ViewInteraction viewPager3 = onView(
                allOf(withId(R.id.container),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(withId(android.R.id.content),0)),1),
                        isDisplayed()));
        viewPager3.perform(swipeLeft());

        // check if list_neighbours is diplayed in the container
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(allOf(withId(R.id.container),
                                withParent(withId(R.id.main_content)))),
                        isDisplayed()));
        recyclerView2.check(matches(isDisplayed()));
        // and contains the two elements added
        recyclerView2.check(withItemCount(2));

        // delete an element and verify if the list is now containing only 1 element
        // click on neighbour to see detail:
        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.item_list_avatar),
                        childAtPosition(childAtPosition(withId(R.id.list_neighbours),1),0),
                        isDisplayed()));
        appCompatImageView3.perform(click());
        // click on add favorite button to cancel favorite status
        ViewInteraction floatingActionButton5 = onView(
                allOf(withId(R.id.button_add_favorite),
                        childAtPosition(
                                childAtPosition(withId(android.R.id.content),0),3),
                        isDisplayed()));
        floatingActionButton5.perform(click());
        // come back to favorites list:
        ViewInteraction floatingActionButton6 = onView(
                allOf(withId(R.id.button_previous_page),
                        childAtPosition(childAtPosition(withId(android.R.id.content),0),4),
                        isDisplayed()));
        floatingActionButton6.perform(click());
        // check if list_neighbours is diplayed in the container
        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(allOf(withId(R.id.container),
                                withParent(withId(R.id.main_content)))),
                        isDisplayed()));
        recyclerView3.check(matches(isDisplayed()));
        // and now contains only one element
        recyclerView3.check(withItemCount(1));
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
