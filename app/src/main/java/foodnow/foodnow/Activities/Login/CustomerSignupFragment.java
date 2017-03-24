package foodnow.foodnow.Activities.Login;

import foodnow.foodnow.R;
import foodnow.foodnow.Activities.Screens.CustomerHome;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//import com.example.aj.scavengersworld.Activities.HomeScreen.HomeScreenActivity;
//import com.example.aj.scavengersworld.Model.User;
//import com.example.aj.scavengersworld.UserSessionManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by vinee on 3/19/2017.
 */

public class CustomerSignupFragment extends Fragment implements View.OnClickListener{
    // FIREBASE STUFF //
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    private EditText mUsername;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mSignUpButton;

    private final String LOG_TAG = getClass().getSimpleName();


    public CustomerSignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate()");

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if(mFirebaseUser != null){
                    // User is signed in
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_in:" + mFirebaseUser.getUid());
                    //setupSession();
                    openHomeScreenCustomerSignup();
                }
                else{
                    // User is signed out
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void bindUItoVariables(View view){
        Log.d(LOG_TAG, "bindUItoVariables()");
        if(view != null) {
            mUsername = (EditText) view.findViewById(R.id.signupUsername);
            mEmail = (EditText) view.findViewById(R.id.signupEmail);
            mPassword = (EditText) view.findViewById(R.id.signupPassword);
            mConfirmPassword = (EditText) view.findViewById(R.id.signupConfirmPassword);
            mSignUpButton = (Button) view.findViewById(R.id.btnSignUp);
            mSignUpButton.setOnClickListener(this);
        }
    }

    private void restoreUIVariables(Bundle savedInstanceState){
        Log.d(LOG_TAG, "restoreUIVariables()");
        if(savedInstanceState != null){
            mUsername.setText(savedInstanceState.getString(getString(R.string.username), ""));
            mEmail.setText(savedInstanceState.getString(getString(R.string.email), ""));
            mPassword.setText(savedInstanceState.getString(getString(R.string.password), ""));
            mConfirmPassword.setText(savedInstanceState.getString(getString(R.string.confirmPassword), ""));
        }
    }

    private Bundle saveUIVariables(Bundle outState){
        Log.d(LOG_TAG, "saveUIVariables()");
        if(outState != null){
            outState.putString(getString(R.string.username), mUsername.getText().toString());
            outState.putString(getString(R.string.email), mEmail.getText().toString());
            outState.putString(getString(R.string.password), mPassword.getText().toString());
            outState.putString(getString(R.string.confirmPassword), mConfirmPassword.getText().toString());
        }

        return outState;
    }

    @Override
    public void onClick(View view) {
        Log.d(LOG_TAG, "onClick()");
        switch (view.getId()){
            case R.id.btnSignUp:
                if(validateInput()){
                    registerUser();
                }
                break;
            default:
                break;
        }
    }

    private void showAlertDialogue(int titleString, int messageString){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(messageString)
                .setTitle(titleString)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean validateInput(){
        Log.d(LOG_TAG, "validateInput()");
        if(!mEmail.getText().toString().trim().contains("@")){
            showAlertDialogue(R.string.invalidEmailTitle, R.string.invalidEmailMessage);
            return false;
        }
        if(mUsername.getText().toString() == null || mUsername.getText().toString().trim().length() == 0){
            showAlertDialogue(R.string.enterUsernameTitle, R.string.enterUsernameMessage);
        }
        if(!mPassword.getText().toString().trim().equals(mConfirmPassword.getText().toString().trim())){
            showAlertDialogue(R.string.passwordMismatch, R.string.passwordMismatchMessage);
            return false;
        }
        return true;
    }

    private void registerUser(){
        Log.d(LOG_TAG, "registerUser()");
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mFirebaseUser = task.getResult().getUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(mUsername.getText().toString().trim())
                                    .build();

                            mFirebaseUser.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(LOG_TAG, "User profile updated.");
                                            }
                                        }
                                    });
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage(task.getException().getMessage())
                                    .setTitle(R.string.login_error_title)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView()");
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(LOG_TAG, "onViewCreated()");
        bindUItoVariables(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "onActivityCreated()");
        restoreUIVariables(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState()");
        saveUIVariables(outState);
    }


  private void openHomeScreenCustomerSignup(){
        Intent homeScreen = new Intent(getActivity(), CustomerHome.class);
        startActivity(homeScreen);
    }

   /* private void setupSession(){
        UserSessionManager sessionManager = UserSessionManager.INSTANCE;
        sessionManager.setUpSession(mFirebaseUser, getContext());
    }
    */
}
