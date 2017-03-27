package foodnow.foodnow.Activities.Screens;

/**
 * Created by vinee on 3/19/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import foodnow.foodnow.R;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CustomerHome extends AppCompatActivity {
    private final String LOG_TAG = getClass().getSimpleName();
    Button mBtnSearchRestaurant;
    Button mBtnNearbyRestaurant;
    Button mBtnPopularRestaurant;
    String usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
                return true;
        }

        return true;
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

}
