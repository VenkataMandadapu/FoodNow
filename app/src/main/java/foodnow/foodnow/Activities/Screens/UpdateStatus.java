package foodnow.foodnow.Activities.Screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import foodnow.foodnow.DatabaseModels.RestaurantStatusDB;
import foodnow.foodnow.Models.CapacityStatusEnum;
import foodnow.foodnow.Models.WaitStatusEnum;
import foodnow.foodnow.R;

public class UpdateStatus extends AppCompatActivity {
    RadioGroup statusradiogroup;
    RadioGroup waitradiogroup;
    CapacityStatusEnum restaurantStatus;
    WaitStatusEnum restaurantWaitTime;
    Button updateStatus;
    String s;
    int numberOfUpdates;
    long timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);
        statusradiogroup = (RadioGroup) findViewById(R.id.statusRadioGroup);
        waitradiogroup = (RadioGroup) findViewById(R.id.waitRadioGroup);
        updateStatus = (Button) findViewById(R.id.statusDetailsUpdate);
        s = getIntent().getExtras().getString("RestaurantId");
        numberOfUpdates = getIntent().getExtras().getInt("NumberOfUpdates");
        timestamp = getIntent().getExtras().getLong("Timestamp");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference statusdatabase = database.getReference("Status"+"/"+s);

        statusradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.statusLow){
                    restaurantStatus = CapacityStatusEnum.LOW;
                } else if (checkedId == R.id.statusMedium){
                    restaurantStatus = CapacityStatusEnum.MEDIUM;
                } else {
                    restaurantStatus = CapacityStatusEnum.FULL;
                }
            }
        });

        waitradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.statusWait0){
                    restaurantWaitTime = WaitStatusEnum.WAIT_0;
                } else if (checkedId == R.id.statusWait15){
                    restaurantWaitTime = WaitStatusEnum.WAIT_15;
                } else if (checkedId == R.id.statusWait30){
                    restaurantWaitTime = WaitStatusEnum.WAIT_30;
                } else if (checkedId == R.id.statusWait45){
                    restaurantWaitTime = WaitStatusEnum.WAIT_45;
                } else {
                    restaurantWaitTime = WaitStatusEnum.WAIT_60;
                }
            }
        });

        updateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantStatusDB currentStatus = new RestaurantStatusDB();
                currentStatus.setName(s);
                currentStatus.setCapacityStatus(restaurantStatus);
                currentStatus.setWaitStatus(restaurantWaitTime);
                long time = System.currentTimeMillis();
                if (time - timestamp < 3600000){
                    currentStatus.setNumberOfUpdates(numberOfUpdates+1);
                    currentStatus.setTimeStamp(timestamp);
                } else {
                    currentStatus.setNumberOfUpdates(1);
                    currentStatus.setTimeStamp(time);
                }
                statusdatabase.setValue(currentStatus);
                Intent openRestaurant = new Intent(v.getContext(),RestaurantStatus.class);
                openRestaurant.putExtra("RestaurantId",s);
                openRestaurant.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                v.getContext().startActivity(openRestaurant);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
                return true;
        }

        return true;
    }
}
