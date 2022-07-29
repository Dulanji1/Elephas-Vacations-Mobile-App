<<<<<<< HEAD
package com.example.sightseeen;
=======
package com.sliit.project_elephas;
>>>>>>> origin/master

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

<<<<<<< HEAD
import com.example.sightseeen.Database.DBHelper;
=======
>>>>>>> origin/master

public class Other_Coast_Details extends AppCompatActivity {
   private Button but1;
   private Button but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other__coast__details);

        but1=(Button)findViewById(R.id.button9);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        but2=(Button)findViewById(R.id.button15);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity1(){
        Intent intent1 = new Intent(this,other_packages.class);
        startActivity(intent1);
    }


    public void openActivity2(){
        Intent intent2 = new Intent(this,Admin_Access_OtherCostPackages.class);
        startActivity(intent2);
    }


}