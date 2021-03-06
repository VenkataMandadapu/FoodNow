package foodnow.foodnow.Activities.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import foodnow.foodnow.Activities.Screens.HomeScreen;
import foodnow.foodnow.Activities.Sessions.SessionManager;
import foodnow.foodnow.DatabaseModels.RestaurantDB;
import foodnow.foodnow.Models.LocationCoordinates;
import foodnow.foodnow.Models.UserTypeEnum;
import foodnow.foodnow.R;

public class SearchRestaurant extends AppCompatActivity {
    private EditText textstring;
    private final String LOG_TAG = getClass().getSimpleName();
    RecyclerView searchview;
    int searchcount = 1;
    SessionManager session;
    UserTypeEnum usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = SessionManager.INSTANCE;
        usertype = session.getUsertype();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);
        textstring = (EditText) findViewById(R.id.searchRestaurant);
        final ArrayList<String> allRestaurants = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference restaurants = database.getReference("Restaurants");
        restaurants.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot a : dataSnapshot.getChildren()){
                    allRestaurants.add(a.getKey());
                }
                Log.d(LOG_TAG,"In wait for database");
                displayFromQuery(allRestaurants,textstring);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void displayFromQuery(final ArrayList<String> allRestaurants, EditText field){
        Log.d(LOG_TAG,"In displayFromQuery");
        field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String t = s.toString().toLowerCase();
                ArrayList<String> matches = new ArrayList<>();
                for (String d : allRestaurants){
                    if(d.toLowerCase().contains(t)){
                        matches.add(d);
                    }
                }
                searchview = (RecyclerView) findViewById(R.id.searchview);
                if (searchcount <= 1){
                    searchview.setLayoutManager(new LinearLayoutManager(SearchRestaurant.this));
                }
                searchview.setAdapter(new SearchRestaurantViewAdapter(matches));

            }

            @Override
            public void afterTextChanged(Editable s) {

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
}
