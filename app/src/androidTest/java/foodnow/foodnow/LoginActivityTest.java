package foodnow.foodnow;

/**
 * Created by Sai on 4/9/2017.
 */

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

import foodnow.foodnow.Activities.Login.CustomerLogin;
import foodnow.foodnow.Activities.Screens.CustomerHome;
import foodnow.foodnow.Activities.Screens.HomeScreen;
import foodnow.foodnow.Activities.Search.SearchRestaurant;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<CustomerLogin> {
    public Solo solo;
    public LoginActivityTest() {
        super(CustomerLogin.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }


    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testScenario1(){
        solo.enterText(0, "abcde@gmail.com");
        solo.enterText(1, "1234567890");
        solo.clickOnButton("Login");
        solo.waitForActivity(CustomerHome.class);
        solo.assertCurrentActivity("HomeScreen Activity", CustomerHome.class, true);
        solo.clickOnView(solo.getView(R.id.action_logout));
        solo.waitForActivity(HomeScreen.class);
        solo.assertCurrentActivity("Login Activity", HomeScreen.class, true);
    }

    public void testScenario2(){
        solo.enterText(0, "abcde@gmail.com");
        solo.enterText(1, "1234567890");
        solo.clickOnButton("Login");
        solo.waitForActivity(CustomerHome.class);
        solo.assertCurrentActivity("Customer Home Activity", CustomerHome.class, true);
        solo.clickOnView(solo.getView(R.id.SearchRestaurant));
        solo.waitForActivity(SearchRestaurant.class);
        solo.assertCurrentActivity("Login Activity", SearchRestaurant.class, true);
        solo.clickOnView(solo.getView(R.id.action_logout));
        solo.waitForActivity(HomeScreen.class);
        solo.assertCurrentActivity("Login Activity", HomeScreen.class, true);
    }
}
