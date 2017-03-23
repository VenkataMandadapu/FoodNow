package foodnow.foodnow.Activities.Search;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.HashMap;

import foodnow.foodnow.Activities.Screens.RestaurantStatus;
import foodnow.foodnow.DatabaseModels.RestaurantDB;
import foodnow.foodnow.R;



public class NearbyRestaurantViewAdapter extends RecyclerView.Adapter<NearbyRestaurantViewAdapter.ViewHolder> {

    private final ArrayList<RestaurantDB> mValues;
    private final NearbyRestaurants.OnListFragmentInteractionListener mListener;
    private final ColorGenerator mGenerator = ColorGenerator.MATERIAL;

    public NearbyRestaurantViewAdapter(ArrayList<RestaurantDB> items, NearbyRestaurants.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_yourhunts, parent, false);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nearbyrestaurant_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RestaurantDB restaurant = mValues.get(position);
        holder.mRestaurantName.setText(restaurant.getName());
        holder.mRestaurantDistance.setText(Double.toString(restaurant.getDistance())+" miles");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.);
                    mListener.onListAllHuntsFeedFragmentInteraction(restaurant);
                }*/
                Intent openRestaurant = new Intent(v.getContext(),RestaurantStatus.class);
                openRestaurant.putExtra("RestaurantId",restaurant.getName());
                v.getContext().startActivity(openRestaurant);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mRestaurantName;
        public final TextView mRestaurantDistance;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mRestaurantName = (TextView) view.findViewById(R.id.nearbyrestaurant_name);
            mRestaurantDistance = (TextView) view.findViewById(R.id.nearbyrestaurant_distance);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mRestaurantName.getText();
        }
    }
}
