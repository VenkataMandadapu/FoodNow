package foodnow.foodnow.Activities.Sessions;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;



/**
 * Created by vinee on 3/19/2017.
 */


public enum SessionManager {
    INSTANCE;

    private FirebaseUser mFirebaseUser;
    private String mUniqueUserId;
    private String mUserName;
    private String mUserEmail;



    //    private SharedPreferences mPref;
//    private Editor mEditor;
    private Context mContext;
//    private int PRIVATE_MODE = 0;
//
//    private static final String PREF_NAME = "Scavengers";
//    private static final String IS_LOGIN = "IsLoggedIn";
//    public static final String KEY_NAME = "username";
//    public static final String KEY_EMAIL = "email";

//    public UserSessionManager getSession(Context context){
//        this.mContext = context;
//        mPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
//        mEditor = mPref.edit();
//        return this;
//    }

//    public void createLoginSession(User user){
//        mEditor.putBoolean(IS_LOGIN, true);
//        mEditor.putString(KEY_NAME, user.getUserName());
//        mEditor.putString(KEY_EMAIL, user.getUserEmail());
//        mEditor.commit();
//    }



      private String userProfileKey;

  /*  public void checkLogin(){
        if(!this.isLoggedIn() && mContext != null){
            Intent i = new Intent(mContext, LoginActivity.class);
            mContext.startActivity(i);
        }
    }
  */

//    public HashMap<String, String> getUserDetails(){
//        HashMap<String, String> user = new HashMap<String, String>();
//        user.put(KEY_NAME, mPref.getString(KEY_NAME, null));
//        user.put(KEY_EMAIL, mPref.getString(KEY_EMAIL, null));
//        return user;
//    }
//
//    public void logoutUser(){
//        mEditor.clear();
//        mEditor.commit();
//        Intent i = new Intent(mContext, LoginActivity.class);
//        mContext.startActivity(i);
//    }

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

