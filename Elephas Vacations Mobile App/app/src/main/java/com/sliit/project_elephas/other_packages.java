package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD

import android.os.Bundle;

public class other_packages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_packages);
    }
}
=======
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class other_packages extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_packages);

    }
    public  void  changefragment(View view){
        Fragment fragment;
        if(view == findViewById(R.id.f1_btn)){
            fragment = new Fragment1();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fgmntDefault,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.f2_btn)){
            fragment = new Fragment2();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fgmntDefault,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.f3_btn)){
            fragment = new Fragment3();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fgmntDefault,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.f4_btn)){
            fragment = new Fragment4();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fgmntDefault,fragment);
            ft.commit();
        }
    }

    }









>>>>>>> origin/master
