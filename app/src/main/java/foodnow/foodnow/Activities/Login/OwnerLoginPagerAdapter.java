package foodnow.foodnow.Activities.Login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by vinee on 3/19/2017.
 */


public class OwnerLoginPagerAdapter  extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private final int LOGIN_TAB = 0;
    private final int SIGNUP_TAB = 1;

    public OwnerLoginPagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case LOGIN_TAB:
                return new OwnerLoginFragment();
            case SIGNUP_TAB:
                return new OwnerSignupFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
