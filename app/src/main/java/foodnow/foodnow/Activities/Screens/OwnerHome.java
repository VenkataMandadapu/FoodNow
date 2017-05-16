package foodnow.foodnow.Activities.Screens;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import foodnow.foodnow.Activities.Search.NearbyRestaurants;
import foodnow.foodnow.Activities.Search.SearchRestaurant;
import foodnow.foodnow.Activities.Search.SearchRestaurantViewAdapter;
import foodnow.foodnow.DatabaseModels.RestaurantDB;
import foodnow.foodnow.R;
import foodnow.foodnow.Activities.Sessions.SessionManager;

public class OwnerHome extends AppCompatActivity {
    private SessionManager session;
    String username;
    RecyclerView ownerrestaurant;
    int restcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);
        session = SessionManager.INSTANCE;
        username = session.getUniqueUserId();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference restaurants = database.getReference("Restaurants");
        final ArrayList<String> allRestaurants = new ArrayList<>();
        restaurants.orderByChild("ownerId").equalTo(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot a : dataSnapshot.getChildren()) {
                    allRestaurants.add(a.getKey());
                    ownerrestaurant = (RecyclerView) findViewById(R.id.ownerrestaurant);
                    if (restcount <= 1) {
                        ownerrestaurant.setLayoutManager(new LinearLayoutManager(OwnerHome.this));
                    }
                    ownerrestaurant.setAdapter(new SearchRestaurantViewAdapter(allRestaurants));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_owner_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_addrestaurant:
                addRestaurant();
                return true;
            case R.id.action_checkrestaurant:
                checkRestaurant();
                return true;
            case R.id.action_ownerlogout:
                logout();
                return true;
        }

        return true;
    }

    public void addRestaurant(){
        Intent addNewRestaurant = new Intent(OwnerHome.this,AddNewRestaurant.class);
        addNewRestaurant.putExtra("UserId",username);
        addNewRestaurant.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        addNewRestaurant.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(addNewRestaurant);
    }

    public void checkRestaurant(){
        Intent checkRestaurant = new Intent(OwnerHome.this,CustomerHome.class);
        checkRestaurant.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        checkRestaurant.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(checkRestaurant);
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
