package foodnow.foodnow.Activities.Screens;

/**
 * Created by vinee on 3/19/2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import foodnow.foodnow.Activities.Login.CustomerLogin;
import foodnow.foodnow.Activities.Search.NearbyRestaurants;
import foodnow.foodnow.Activities.Search.PopularRestaurants;
import foodnow.foodnow.Activities.Search.SearchRestaurant;
import foodnow.foodnow.Activities.Sessions.SessionManager;
import foodnow.foodnow.Models.UserTypeEnum;
import foodnow.foodnow.R;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerHome extends AppCompatActivity {
    private final String LOG_TAG = getClass().getSimpleName();
    Button mBtnSearchRestaurant;
    Button mBtnNearbyRestaurant;
    Button mBtnPopularRestaurant;
    SessionManager session;
    UserTypeEnum usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = SessionManager.INSTANCE;
        usertype = session.getUsertype();

        if(usertype == UserTypeEnum.CUSTOMER || usertype == UserTypeEnum.OWNER) {
            setContentView(R.layout.activity_customer_home);
        }
        else if (usertype == UserTypeEnum.GUEST) {
            setContentView(R.layout.activity_guest_home);
            if(!isOnline()) {
                handleOffline();
            }

        }

        Log.d(LOG_TAG,"In Customer Home On Create");

        mBtnSearchRestaurant = (Button) findViewById(R.id.SearchRestaurant);
        mBtnNearbyRestaurant = (Button) findViewById(R.id.nearbyRestaurants);
        mBtnPopularRestaurant = (Button) findViewById(R.id.popularRestaurants);

        mBtnSearchRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchrestaurant = new Intent(CustomerHome.this,SearchRestaurant.class);
                searchrestaurant.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                searchrestaurant.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(searchrestaurant);


            }
        });

        mBtnNearbyRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nearbyrestaurant = new Intent(CustomerHome.this,NearbyRestaurants.class);
                nearbyrestaurant.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nearbyrestaurant.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(nearbyrestaurant);


            }
        });

        mBtnPopularRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popularrestaurant = new Intent(CustomerHome.this,PopularRestaurants.class);
                popularrestaurant.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                popularrestaurant.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(popularrestaurant);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(usertype != UserTypeEnum.GUEST) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;
        }

        return true;
    }

    public boolean isOnline() {
        boolean connected = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;


        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }

    private void handleOffline(){
        Toast.makeText(this,getString(R.string.offline_toast),Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.offline_display_message))
                .setCancelable(false);
        alertDialogBuilder.setPositiveButton(getString(R.string.offline_goto_settings), new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), 3);

            }
        });
        alertDialogBuilder.setNegativeButton(getString(R.string.offline_close_app),
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                        finish();
                        System.exit(0);
                        //openHomeScreen();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG,"In Customer Home On Resume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG,"In Customer Home On Pause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG,"In Customer Home On Stop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG,"In Customer Home On Destroy");
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        clearSession();
        openHomeScreen();
    }

    private void openHomeScreen(){
        Intent homeScreen = new Intent(this, HomeScreen.class);
        homeScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeScreen);
        finish();
    }

    private void clearSession(){
        SessionManager sessionManager = SessionManager.INSTANCE;
        sessionManager.clearSession();
    }

    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
