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


            mBtnCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent customer_login = new Intent(HomeScreen.this,CustomerLogin.class);
                    startActivity(customer_login);


                }
            });

            mBtnOwner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent owner_login = new Intent(HomeScreen.this,OwnerLogin.class);
                    startActivity(owner_login);

                }
            });

            mBtnGuest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent guest = new Intent(HomeScreen.this,GuestHome.class);
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

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_landing_page, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    private void setupSession(){
        SessionManager sessionManager = SessionManager.INSTANCE;
        sessionManager.setUpSession(null, null, UserTypeEnum.GUEST);
    }

}


