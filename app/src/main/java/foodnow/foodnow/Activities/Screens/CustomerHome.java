package foodnow.foodnow.Activities.Screens;

/**
 * Created by vinee on 3/19/2017.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import foodnow.foodnow.R;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class CustomerHome extends AppCompatActivity {
    private final String LOG_TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        Log.d(LOG_TAG,"In Customer Home On Create");


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
