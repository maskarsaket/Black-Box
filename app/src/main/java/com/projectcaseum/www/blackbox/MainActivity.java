package com.projectcaseum.www.blackbox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private String email;
    private String password;
    private Button signup;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        signup = (Button) findViewById(R.id.signUp_button);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("qwertyuiop", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("asdfghjkl", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = findViewById(R.id.signUp_password).toString();
                email = findViewById(R.id.signUp_email).toString();
                setUpAcc(email, password);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    protected void setUpAcc(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("qwerty", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

//
//    mAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            Log.d("j", "signInWithEmail:onComplete:" + task.isSuccessful());
//
//            // If sign in fails, display a message to the user. If sign in succeeds
//            // the auth state listener will be notified and logic to handle the
//            // signed in user can be handled in the listener.
//            if (!task.isSuccessful()) {
//                Log.w("k", "signInWithEmail", task.getException());
//                Toast.makeText(MainActivity.this, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            // ...
//        }
//    });
    }
}
