package foodnow.foodnow.Activities.Screens;

import foodnow.foodnow.Activities.Login.CustomerLogin;
import foodnow.foodnow.Activities.Login.OwnerLogin;
import foodnow.foodnow.Activities.Sessions.SessionManager;
import foodnow.foodnow.Models.UserTypeEnum;
import foodnow.foodnow.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;



/**
 * Created by vinee on 3/18/2017.
 */

public class HomeScreen extends AppCompatActivity{

        Button mBtnCustomer;
        Button mBtnOwner;
        Button mBtnGuest;
        SessionManager session;
        UserTypeEnum usertype;
        private final String LOG_TAG = getClass().getSimpleName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_landing_page);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            mBtnCustomer = (Button) findViewById(R.id.Customer);
            mBtnOwner = (Button) findViewById(R.id.Owner);
            mBtnGuest = (Button) findViewById(R.id.Guest);
            setSupportActionBar(toolbar);
            Log.d(LOG_TAG,"In Home On Create");

            session = SessionManager.INSTANCE;
            usertype = session.getUsertype();
            if (usertype != null){
                if (usertype == UserTypeEnum.CUSTOMER) {
                    Intent homeScreen = new Intent(this, CustomerHome.class);
                    homeScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    homeScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(homeScreen);
                    finish();
                }
                else if (usertype == UserTypeEnum.OWNER) {
                    Intent homeScreen = new Intent(this, OwnerHome.class);
                    homeScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    homeScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(homeScreen);
                    finish();
                }

            }


            mBtnCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent customer_login = new Intent(HomeScreen.this,CustomerLogin.class);
                    startActivity(customer_login);
                    finish();


                }
            });

            mBtnOwner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent owner_login = new Intent(HomeScreen.this,OwnerLogin.class);
                    startActivity(owner_login);
                    finish();

                }
            });

            mBtnGuest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent guest = new Intent(HomeScreen.this,CustomerHome.class);
                    setupSession();
                    startActivity(guest);

                }
            });
        }
        @Override
        public void onResume(){
            super.onResume();
            Log.d(LOG_TAG,"In Home On Resume");
        }

        @Override
        public void onPause(){
            super.onPause();
            Log.d(LOG_TAG,"In Home On Pause");
        }

        @Override
        public void onStop(){
            super.onStop();
            Log.d(LOG_TAG,"In Home On Stop");
        }

        @Override
        public void onDestroy(){
            super.onDestroy();
            Log.d(LOG_TAG,"In Home On Destroy");
        }


        private void setupSession(){
            SessionManager sessionManager = SessionManager.INSTANCE;
            sessionManager.setUpSession(null, null, UserTypeEnum.GUEST);
        }

}


