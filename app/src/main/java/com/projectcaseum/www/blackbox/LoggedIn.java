package com.projectcaseum.www.blackbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.x;

public class LoggedIn extends AppCompatActivity {

    private Button sOut;
    private TextView email;
    private TextView name;
    private DatabaseReference ref= FirebaseDatabase.getInstance().getReference().getRoot();


    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        sOut= (Button)findViewById(R.id.SignOut);
        email = (TextView)findViewById(R.id.user_email);
        name = (TextView)findViewById(R.id.user_name);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                String data="";
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String num = postSnapshot.getValue().toString();
                    data=data+" "+num+"\n";

                }
                name.setText(data + "\n");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        ref.addValueEventListener(postListener);



        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
        if(FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toString()!=null){
        //name.setText("hey "+ FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toString() + ", Scene kya hai??!, Scene kya hai??! Mera sab sahi hai..tera bol Scene kya hai??!");
            }
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


