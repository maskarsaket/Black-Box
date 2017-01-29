package com.projectcaseum.www.blackbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoggedIn extends AppCompatActivity {

    private Button sOut;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        sOut= (Button)findViewById(R.id.SignOut);
        email = (TextView)findViewById(R.id.user_email);
        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
        sOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i =new Intent(LoggedIn.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
