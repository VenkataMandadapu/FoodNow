package foodnow.foodnow.Activities.Screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import foodnow.foodnow.Activities.Search.NearbyRestaurantViewAdapter;
import foodnow.foodnow.Activities.Search.NearbyRestaurants;
import foodnow.foodnow.DatabaseModels.RestaurantDB;
import foodnow.foodnow.DatabaseModels.RestaurantStatusDB;
import foodnow.foodnow.R;

public class RestaurantStatus extends AppCompatActivity {
    String s;
    private final String LOG_TAG = getClass().getSimpleName();
    TextView restaurantname;
    TextView restaurantstatus;
    TextView restaurantwait;
    Button updateStatus;
    long timestamp;
    int numberOfUpdates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_status);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference restaurants = database.getReference("Restaurants");
        s = getIntent().getStringExtra("RestaurantId");
        restaurantname = (TextView)  findViewById(R.id.statusRestaurantName);
        restaurantstatus = (TextView) findViewById(R.id.statusRestaurantStatus);
        restaurantwait = (TextView) findViewById(R.id.statusRestaurantWait);
        updateStatus = (Button) findViewById(R.id.statusUpdate);

        Log.d(LOG_TAG,"In OnCreate of Restaurant Status");

        restaurants.orderByChild("name").equalTo(s).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(LOG_TAG,dataSnapshot.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final DatabaseReference status = database.getReference("Status");
        status.orderByChild("name").equalTo(s).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot a: dataSnapshot.getChildren()){
                    RestaurantStatusDB details = a.getValue(RestaurantStatusDB.class);
                    restaurantname.setText(details.getName());
                    restaurantstatus.setText("Current Status: "+details.getCapacityStatus().toString());
                    restaurantwait.setText("Current Wait Time:"+details.getWaitStatus().toString());
                    numberOfUpdates = details.getNumberOfUpdates();
                    timestamp = details.getTimeStamp();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        updateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRestaurant = new Intent(v.getContext(),UpdateStatus.class);
                openRestaurant.putExtra("RestaurantId",s);
                openRestaurant.putExtra("NumberOfUpdates",numberOfUpdates);
                openRestaurant.putExtra("Timestamp",timestamp);
                openRestaurant.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                v.getContext().startActivity(openRestaurant);
            }
        });
    }
}
