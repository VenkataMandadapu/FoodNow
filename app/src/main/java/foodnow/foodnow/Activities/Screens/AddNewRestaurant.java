package foodnow.foodnow.Activities.Screens;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.facebook.login.LoginManager;

import foodnow.foodnow.Activities.Sessions.SessionManager;
import foodnow.foodnow.DatabaseModels.RestaurantDB;
import foodnow.foodnow.DatabaseModels.RestaurantStatusDB;
import foodnow.foodnow.Models.CapacityStatusEnum;
import foodnow.foodnow.Models.LocationCoordinates;
import foodnow.foodnow.Models.UserTypeEnum;
import foodnow.foodnow.Models.WaitStatusEnum;
import foodnow.foodnow.R;

public class AddNewRestaurant extends AppCompatActivity {
    Button addnewRestuarant;
    EditText restlatitude;
    EditText restlongitude;
    EditText restcuisine;
    EditText restname;
    EditText restaddress;
    EditText restphone;
    SessionManager session;
    UserTypeEnum usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_restaurant);
        addnewRestuarant = (Button) findViewById(R.id.addNewRestaurant);
        restname = (EditText) findViewById(R.id.addrestaurant_name);
        restaddress = (EditText) findViewById(R.id.addrestaurant_address);
        restphone = (EditText) findViewById(R.id.addrestaurant_phone);
        restcuisine = (EditText) findViewById(R.id.addrestaurant_cuisine);
        restlatitude = (EditText) findViewById(R.id.addrestaurant_latitude);
        restlongitude = (EditText) findViewById(R.id.addrestaurant_longitude) ;
        String username = getIntent().getStringExtra("UserId");

        session = SessionManager.INSTANCE;
        usertype = session.getUsertype();

        addnewRestuarant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantDB restaurant = new RestaurantDB();
                RestaurantStatusDB reststatus = new RestaurantStatusDB();
                LocationCoordinates location = new LocationCoordinates();

                if ((restname.getText().toString() == null) || (restname.getText().toString() == null) || (restphone.getText().toString() == null) || (restcuisine.getText().toString() == null) || (restlongitude.getText().toString() == null) || (restlatitude.getText().toString() == null)) {
                    restaurant.setName(restname.getText().toString());
                    restaurant.setAddress(restaddress.getText().toString());
                    restaurant.setPhone(restphone.getText().toString());
                    restaurant.setCuisine(restcuisine.getText().toString());
                    location.setLongitude(Double.parseDouble(restlongitude.getText().toString()));
                    location.setLatitude(Double.parseDouble(restlatitude.getText().toString()));
                    restaurant.setCoordinates(location);
                    restaurant.setOwnerId(getIntent().getStringExtra("UserId"));
                    reststatus.setName(restname.getText().toString());
                    reststatus.setNumberOfUpdates(1);
                    reststatus.setTimeStamp(System.currentTimeMillis());
                    reststatus.setWaitStatus(WaitStatusEnum.WAIT_0);
                    reststatus.setCapacityStatus(CapacityStatusEnum.LOW);
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference restaurants = database.getReference("Restaurants/" + restaurant.getName());
                    final DatabaseReference status = database.getReference("Status/" + restaurant.getName());
                    restaurants.setValue(restaurant);
                    status.setValue(reststatus);
                    Intent openowner = new Intent(AddNewRestaurant.this, OwnerHome.class);
                    openowner.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(openowner);
                } else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(AddNewRestaurant.this);
                    dialog.setTitle("Empty Fields")
                            .setMessage("Please fill all the fields");
                    AlertDialog alert = dialog.create();
                    alert.show();
                }
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
