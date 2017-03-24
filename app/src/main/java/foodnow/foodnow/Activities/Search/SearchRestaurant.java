package foodnow.foodnow.Activities.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import foodnow.foodnow.DatabaseModels.RestaurantDB;
import foodnow.foodnow.Models.LocationCoordinates;
import foodnow.foodnow.R;

public class SearchRestaurant extends AppCompatActivity {
    private EditText textstring;
    private final String LOG_TAG = getClass().getSimpleName();
    RecyclerView searchview;
    int searchcount = 1;
    private NearbyRestaurants.OnListFragmentInteractionListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                //String input = s.toString();
                ArrayList<String> matches = new ArrayList<>();
                //Pattern p = Pattern.compile(input);
                for (String d : allRestaurants){
                    if(d.contains(s)){
                        matches.add(d);
                    }
                }
                searchview = (RecyclerView) findViewById(R.id.searchview);
                if (searchcount <= 1){
                    searchview.setLayoutManager(new LinearLayoutManager(SearchRestaurant.this));
                }
                searchview.setAdapter(new SearchRestaurantViewAdapter(matches,mListener));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
