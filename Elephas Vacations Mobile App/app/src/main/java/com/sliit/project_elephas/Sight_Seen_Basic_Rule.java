<<<<<<< HEAD
package com.example.sightseeen;
=======
package com.sliit.project_elephas;
>>>>>>> origin/master

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sight_Seen_Basic_Rule extends AppCompatActivity {

private Button but2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight__seen__basic__rule);

        but2=findViewById(R.id.button11);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity2(){
        Intent intent2 = new Intent(this,Sight_Seen_Basic_Rule2.class);
        startActivity(intent2);
    }
}
