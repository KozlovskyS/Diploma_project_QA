package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.Data.rightLogin;
import static ru.iteco.fmhandroid.ui.data.Data.rightPassword;

import android.content.Context;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.Root;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.page.LoginPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NavBar;

public class DataHelper {


    public static ViewInteraction okButton = onView(
            allOf(withId(android.R.id.button1), withText("OK")));


    public static void enterData(ViewInteraction fieldName, String text) {
        fieldName.check(matches(isDisplayed())).perform(replaceText(text), closeSoftKeyboard());
    }

    public static void tapButton(ViewInteraction buttonName) {
        buttonName.check(matches(isDisplayed())).perform(click());
    }

    public static void waitFor(long millis) {
        step("Ждем " + millis/1000 + " сек");
        SystemClock.sleep(millis);
    }
    public static void logInCheck() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = new MainPage();
        try {
            step("Ожидание страницы авторизации");
            waitFor(1000);
            mainPage.waitingPageToLoad();
            mainPage.checkPageLoaded();
        } catch (Exception e) {
            step("Авторизация с валидными данными");
            loginPage.waitingPageToLoad();
            loginPage.logIn(rightLogin, rightPassword);
            mainPage.waitingPageToLoad();
        }
    }
    public static void logOutCheck() {
        step("Выход из учетной записи");
        LoginPage loginPage = new LoginPage();
        NavBar navBar = new NavBar();
        MainPage mainPage = new MainPage();
        try {
            step("Ожидание страницы авторизации");
            loginPage.waitingPageToLoad();
            loginPage.checkPageLoaded();
        } catch (AssertionError e) {
            step("Выход из учетной записи");
            mainPage.waitingPageToLoad();
            navBar.logOut();
        }
    }


    /*** Perform action of waiting for a specific view id. */
    // wait during 15 seconds for a view
    //onView(isRoot()).perform(waitId(R.id.dialogEditor, TimeUnit.SECONDS.toMillis(15)));
    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    /** метод для определения всплывающих сообщений*/
    public static class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    return true;
                }
            }
            return false;
        }
    }

    public static ViewInteraction emptyToast(int id) {
        return onView(withText(id)).inRoot(new DataHelper.ToastMatcher());
    }

    public static ViewInteraction checkToast(int id, boolean visible) {
        if (visible) {
            emptyToast(id).check(matches(isDisplayed()));
        } else {
            emptyToast(id).check(matches(not(isDisplayed())));
        }
        return null;
    }

    public static ViewAction waitDisplayed(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {

                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> has been displayed during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> matchId = withId(viewId);
                final Matcher<View> matchDisplayed = isDisplayed();

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (matchId.matches(child) && matchDisplayed.matches(child)) {
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(ViewGroup.class), isDisplayed());
            }

            @Override
            public String getDescription() {

                return "click child view with id " + id;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View childView = view.findViewById(id);
                childView.performClick();
            }
        };
    }

    public static class RecyclerViewMatcher {
        private final int recyclerViewId;

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
            return new RecyclerViewMatcher(recyclerViewId);
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException var4) {
                            idDescription = String.format("%s (resource not found)", recyclerViewId);
                        }
                    }

                    description.appendText("with id: " + idDescription);
                }

                public boolean matchesSafely(View view) {

                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                            if (viewHolder != null) {
                                childView = viewHolder.itemView;
                            }
                        } else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }
                }
            };
        }
    }

    public static String generateScreenshotName(String testName) {   //нужно для allure отчета
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        return testName + "_" + timestamp + ".png";
    }
    public static void tapOkButton() {
        step("Нажать ОК в системном окне");
        okButton.check(matches(isDisplayed()));
        tapButton(okButton);
    }

    public static String generateRandomFourDigitString() { // Генерация случайного числа от 0 до 9999
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        return String.format("%04d", randomNumber);
    }

    public static void waitElement(int id) {

        onView(isRoot()).perform(waitDisplayed(id, 5000));
    }

//    public static String getStringFromResource(int resourceId) {
//        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        return targetContext.getResources().getString(resourceId);
//    }


//    public static class GetHeightAfterClickViewAction implements ViewAction {
//
//        private int[] heightAfterClick;
//
//        public GetHeightAfterClickViewAction(int[] heightAfterClick) {
//            this.heightAfterClick = heightAfterClick;
//        }
//
//        @Override
//        public Matcher<View> getConstraints() {
//            return isAssignableFrom(RecyclerView.class);
//        }
//
//        @Override
//        public String getDescription() {
//            return "Get height after click";
//        }
//
//        @Override
//        public void perform(UiController uiController, View view) {
//            RecyclerView recyclerView = (RecyclerView) view;
//            View firstItem = recyclerView.getChildAt(0);
//            if (firstItem != null) {
//                heightAfterClick[0] = firstItem.getHeight();
//            }
//        }
//    }


//    public static void waitUntilVisible(View view) {
//        final CountDownLatch latch = new CountDownLatch(1);
//        ViewTreeObserver observer = view.getViewTreeObserver();
//        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (view.isShown()) {
//                    latch.countDown();
//                }
//            }
//        });
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }




//    public static void waitUntilVisible(ViewInteraction inRoot) {
//    }


//    public static String getTextFromNews(int fieldId, int position) {
//        final String[] itemDateText = new String[1];
//        onView(RecyclerViewMatcher.withRecyclerView(R.id.news_list_recycler_view).atPosition(position))
//                .check((view, noViewFoundException) -> {
//                    if (noViewFoundException != null) {
//                        throw noViewFoundException;
//                    }
//                    TextView dateTextView = view.findViewById(fieldId);
//                    itemDateText[0] = dateTextView.getText().toString();
//                });
//        return itemDateText[0];
//    }


}
