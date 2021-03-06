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

import foodnow.foodnow.Activities.Login.OwnerLogin;
import foodnow.foodnow.Activities.Screens.AddNewRestaurant;
import foodnow.foodnow.Activities.Screens.HomeScreen;
import foodnow.foodnow.Activities.Screens.OwnerHome;

public class OwnerLoginTestActivity extends ActivityInstrumentationTestCase2<OwnerLogin>{
    public OwnerLoginTestActivity() {
        super(OwnerLogin.class);
    }
    public Solo solo;

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
        solo.waitForActivity(OwnerHome.class);
        solo.assertCurrentActivity("HomeScreen Activity", OwnerHome.class, true);
        solo.clickOnMenuItem("Logout");
        solo.waitForActivity(HomeScreen.class);
        solo.assertCurrentActivity("Login Activity", HomeScreen.class, true);
    }

    public void testScenario2(){
        solo.enterText(0, "abcde@gmail.com");
        solo.enterText(1, "1234567890");
        solo.clickOnButton("Login");
        solo.waitForActivity(OwnerHome.class);
        solo.assertCurrentActivity("HomeScreen Activity", OwnerHome.class, true);
        solo.clickOnMenuItem("Add/Update Restaurant");
        solo.waitForActivity(AddNewRestaurant.class);
        solo.enterText(0, "New Rest");
        solo.enterText(1, "1234");
        solo.enterText(2, "American");
        solo.enterText(3, "Columbus, Ohio");
        solo.enterText(4, "123");
        solo.enterText(5, "234");
        solo.clickOnButton("Add/Update Restaurant");
        solo.waitForActivity(OwnerHome.class);
        solo.assertCurrentActivity("HomeScreen Activity", OwnerHome.class, true);
        solo.clickOnMenuItem("Logout");
        solo.waitForActivity(HomeScreen.class);
        solo.assertCurrentActivity("Login Activity", HomeScreen.class, true);



    }
}
