package foodnow.foodnow.Activities.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import foodnow.foodnow.Activities.Screens.RestaurantStatus;
import foodnow.foodnow.DatabaseModels.RestaurantStatusDB;
import foodnow.foodnow.R;

public class PopularRestaurants extends AppCompatActivity {
    RecyclerView popularview;
    int nearbycount;
    private NearbyRestaurants.OnListFragmentInteractionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_restaurants);
        final ArrayList<RestaurantStatusDB> popularRestaurants = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference restaurants = database.getReference("Status");

        restaurants.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot a: dataSnapshot.getChildren()){
                    RestaurantStatusDB restaurant = a.getValue(RestaurantStatusDB.class);
                    if(restaurant.getNumberOfUpdates() >= 5 && (System.currentTimeMillis() - restaurant.getTimeStamp()) < 3600000){
                        popularRestaurants.add(restaurant);
                    }
                }

                popularview = (RecyclerView) findViewById(R.id.popularview);
                if (nearbycount <= 1){
                    popularview.setLayoutManager(new LinearLayoutManager(PopularRestaurants.this));
                }
                popularview.setAdapter(new PopularRestaurantViewAdapter(popularRestaurants,mListener));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
