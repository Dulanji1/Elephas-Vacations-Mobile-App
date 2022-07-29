package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

import android.view.View;

public class RegistrationActivity extends AppCompatActivity {

    //Button btnuser, btnadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //default fragment should be displayed, here it is = user insertion
        Fragment fragment = new UserRegistrationFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, fragment).commit();

    }

    //this method is to change the fragment view
    public void changeFragment(View view) {

        Fragment fragment;
        if (view == findViewById(R.id.btnUSER)) {
            fragment = new UserRegistrationFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.commit();
        }

        if (view == findViewById(R.id.btnADMIN)) {

            fragment = new AdminRegistrationFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.commit();
        }
    }

}


