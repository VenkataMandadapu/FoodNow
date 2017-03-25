package foodnow.foodnow.Activities.Sessions;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.ProtocolException;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import foodnow.foodnow.Models.UserTypeEnum;


/**
 * Created by vinee on 3/19/2017.
 */


public enum SessionManager {
    INSTANCE;

    private FirebaseUser mFirebaseUser;
    private String mUniqueUserId;
    private String mUserName;
    private String mUserEmail;
    private UserTypeEnum mUserType;

    private Context mContext;
    private String userProfileKey;

    public void setmUserType (UserTypeEnum mUserType) {
        this.mUserType = mUserType;
    }

    public UserTypeEnum getmUserType() {
        return mUserType;
    }

    public boolean isLoggedIn(){
        return this.mFirebaseUser != null;
    }

    public String getUniqueUserId(){
        return this.mUniqueUserId;
    }

    public String getUserEmailId(){
        return this.mUserEmail;
    }

    public String getUserName(){
        return this.mUserName;
    }

    public String getUserProfileKey(){ return this.userProfileKey; }

    public void setUpSession(FirebaseUser firebaseUser, Context context){
        if(firebaseUser != null && context != null){
            this.mContext = context;
            this.mFirebaseUser = firebaseUser;
            this.mUniqueUserId = firebaseUser.getUid();
            this.mUserName = firebaseUser.getDisplayName();
            this.mUserEmail = firebaseUser.getEmail();
        }
    }


    public void clearSession(){
        this.mFirebaseUser = null;
        this.mUserEmail = null;
        this.mUserName = null;
        this.mUniqueUserId = null;
        this.mContext = null;


    }

}

