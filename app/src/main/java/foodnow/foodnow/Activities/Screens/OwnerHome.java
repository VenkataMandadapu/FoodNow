package foodnow.foodnow.Activities.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import foodnow.foodnow.Activities.Search.NearbyRestaurants;
import foodnow.foodnow.Activities.Search.SearchRestaurant;
import foodnow.foodnow.R;

/**
 * Created by vinee on 3/23/2017.
 */

public class OwnerHome extends AppCompatActivity {
    private final String LOG_TAG = getClass().getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_customer_home);

        Log.d(LOG_TAG,"In Customer Home On Create");



    }
}
