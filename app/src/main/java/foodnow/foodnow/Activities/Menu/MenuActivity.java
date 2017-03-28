package foodnow.foodnow.Activities.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import foodnow.foodnow.Activities.Sessions.SessionManager;
import foodnow.foodnow.Activities.Screens.HomeScreen;

/**
 * Created by vinee on 3/24/2017.
 */

public class MenuActivity extends AppCompatActivity {


    public void logout(){
        FirebaseAuth.getInstance().signOut();
        clearSession();
        openHomeScreen();
    }

    protected void openHomeScreen(){
        Intent homeScreen = new Intent(this, HomeScreen.class);
        homeScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeScreen);
    }

    private void clearSession(){
        SessionManager sessionManager = SessionManager.INSTANCE;
        sessionManager.clearSession();
    }
}
