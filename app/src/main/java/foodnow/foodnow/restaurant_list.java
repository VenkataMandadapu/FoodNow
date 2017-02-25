package foodnow.foodnow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class restaurant_list extends AppCompatActivity {
    private final String LOG_TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(LOG_TAG,"In resturant_list On Create");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG,"In resturant_list On Resume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG,"In resturant_list On Pause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG,"In resturant_list On Stop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG,"In resturant_list On Destroy");
    }

}
