package com.sliit.project_elephas;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.snapshot.Index;
=======
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

//import com.google.firebase.database.DatabaseReference;
>>>>>>> origin/master

public class Main_Activity_Dula extends AppCompatActivity {
    private Button but1;
    private Button but2;
<<<<<<< HEAD
=======
    private Button but3;
    private Button but4;
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main___dula);

<<<<<<< HEAD
        but1=(Button) findViewById(R.id.p1but1);
=======

        but1 = (Button) findViewById(R.id.p1but1);
        but2 = (Button) findViewById(R.id.dubut2);

>>>>>>> origin/master
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
<<<<<<< HEAD
        });
        but2 = (Button) findViewById(R.id.dubut2);
=======

        });


>>>>>>> origin/master
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
<<<<<<< HEAD
    }
    public void openActivity1(){
        Intent intent1 = new Intent(this,sightseen.class);
        startActivity(intent1);
    }
    public void openActivity2(){
        Intent intent2 = new Intent(this,other_cost_details.class);
        startActivity(intent2);
    }
}
=======

    }
    public void openActivity1(){
        Intent intent = new Intent(this,sightseen.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent2 = new Intent(this,Other_Coast_Details.class);
        startActivity(intent2);
    }

}
>>>>>>> origin/master
