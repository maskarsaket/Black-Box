package com.projectcaseum.www.blackbox;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Fragment that displays "Monday".
 */
public class MapFragments extends Fragment  {

    private Button mOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        mOut = (Button)rootView.findViewById(R.id.MapButton);
        mOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i =new Intent(getActivity(),MApActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }

}

