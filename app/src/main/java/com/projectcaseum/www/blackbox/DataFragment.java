package com.projectcaseum.www.blackbox;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Fragment that displays "Monday".
 */
public class DataFragment extends Fragment {

    private Button sOut;
    private TextView email;
    private TextView name;
    private DatabaseReference ref= FirebaseDatabase.getInstance().getReference().getRoot();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_data, container, false);




        sOut = (Button)rootView.findViewById(R.id.SignOut);

        email = (TextView)rootView.findViewById(R.id.user_email);
        name = (TextView)rootView.findViewById(R.id.user_name);

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
                Intent i =new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        });

        return rootView;

    }

}