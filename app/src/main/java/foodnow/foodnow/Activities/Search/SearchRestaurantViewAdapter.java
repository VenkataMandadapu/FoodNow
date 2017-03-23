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

import foodnow.foodnow.Activities.Screens.RestaurantStatus;
import foodnow.foodnow.R;



public class SearchRestaurantViewAdapter extends RecyclerView.Adapter<SearchRestaurantViewAdapter.ViewHolder> {

    private final ArrayList<String> mValues;
    private final NearbyRestaurants.OnListFragmentInteractionListener mListener;
    private final ColorGenerator mGenerator = ColorGenerator.MATERIAL;

    public SearchRestaurantViewAdapter(ArrayList<String> items, NearbyRestaurants.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_yourhunts, parent, false);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searchrestaurant_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String restaurant = mValues.get(position);
        holder.mRestaurantName.setText(restaurant);

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
                openRestaurant.putExtra("RestaurantId",restaurant);
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

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mRestaurantName = (TextView) view.findViewById(R.id.searchrestaurant_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mRestaurantName.getText();
        }
    }
}
