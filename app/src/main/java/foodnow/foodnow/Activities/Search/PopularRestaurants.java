package foodnow.foodnow.Activities.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import foodnow.foodnow.Activities.Screens.HomeScreen;
import foodnow.foodnow.Activities.Screens.RestaurantStatus;
import foodnow.foodnow.Activities.Sessions.SessionManager;
import foodnow.foodnow.DatabaseModels.RestaurantStatusDB;
import foodnow.foodnow.Models.UserTypeEnum;
import foodnow.foodnow.R;

public class PopularRestaurants extends AppCompatActivity {
    RecyclerView popularview;
    int nearbycount;
    private NearbyRestaurants.OnListFragmentInteractionListener mListener;
    SessionManager session;
    UserTypeEnum usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        session = SessionManager.INSTANCE;
        usertype = session.getUsertype();

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
    private void logout(){
        FirebaseAuth.getInstance().signOut();
        clearSession();
        openHomeScreen();
    }

    private void openHomeScreen(){
        Intent homeScreen = new Intent(this, HomeScreen.class);
        homeScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeScreen);
    }

    private void clearSession(){
        SessionManager sessionManager = SessionManager.INSTANCE;
        sessionManager.clearSession();
    }
}
