package foodnow.foodnow.Activities.Search;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import foodnow.foodnow.Activities.Screens.HomeScreen;
import foodnow.foodnow.Activities.Sessions.SessionManager;
import foodnow.foodnow.DatabaseModels.RestaurantDB;
import foodnow.foodnow.Models.UserTypeEnum;
import foodnow.foodnow.R;

public class NearbyRestaurants extends AppCompatActivity {
    LocationManager locationManager;
    String locationProvider = LocationManager.NETWORK_PROVIDER;
    private final String LOG_TAG = getClass().getSimpleName();
    RecyclerView nearbyview;
    private int nearbycount = 1;
    private OnListFragmentInteractionListener mListener;
    SessionManager session;
    UserTypeEnum usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        session = SessionManager.INSTANCE;
        usertype = session.getUsertype();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_restaurants);
        final ArrayList<RestaurantDB> nearbyRestaurants = new ArrayList<>();
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Log.d(LOG_TAG,"In Nearby Restaurant onCreate");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            new android.app.AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Please give permissions for the app to use location")
                    .show();
            return;
        }
        if (!isLocationEnabled()) {
            showAlert();
        }
        else {
            final Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            Log.d(LOG_TAG, "LastKnown :" + lastKnownLocation.toString());
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference restaurants = database.getReference("Restaurants");
            restaurants.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot a : dataSnapshot.getChildren()) {
                        Log.d(LOG_TAG, a.getKey());
                        RestaurantDB details = a.getValue(RestaurantDB.class);
                        int r = 6371; // average radius of the earth in km
                        double dLat = Math.toRadians(details.getCoordinates().getLatitude() - lastKnownLocation.getLatitude());
                        double dLon = Math.toRadians(details.getCoordinates().getLongitude() - lastKnownLocation.getLongitude());
                        double dist = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                                Math.cos(Math.toRadians(lastKnownLocation.getLatitude())) * Math.cos(Math.toRadians(details.getCoordinates().getLatitude()))
                                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
                        double c = 2 * Math.atan2(Math.sqrt(dist), Math.sqrt(1 - dist));
                        double distance = r * c * 0.62; //Converting km to miles
                        Log.d(LOG_TAG, Double.toString(distance));
                        if (distance < 2) {
                            details.setDistance(Math.round(distance * 100.0) / 100.0);
                            nearbyRestaurants.add(details);

                        }
                    }

                    nearbyview = (RecyclerView) findViewById(R.id.nearbyview);
                    if (nearbycount <= 1) {
                        nearbyview.setLayoutManager(new LinearLayoutManager(NearbyRestaurants.this));
                    }
                    nearbyview.setAdapter(new NearbyRestaurantViewAdapter(nearbyRestaurants, mListener));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
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

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert() {
        Toast.makeText(this,"Enable GPS",Toast.LENGTH_SHORT).show();
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setCancelable(false)
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        paramDialogInterface.cancel();
                        finish();
                        System.exit(0);
                    }
                });
        AlertDialog alert = dialog.create();
        alert.show();
    }

    public interface OnListFragmentInteractionListener{
        void onListAllHuntsFeedFragmentInteraction(RestaurantDB restaurant);
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