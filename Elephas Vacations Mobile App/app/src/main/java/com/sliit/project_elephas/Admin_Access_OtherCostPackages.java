<<<<<<< HEAD
package com.example.sightseeen;
=======
package com.sliit.project_elephas;
>>>>>>> origin/master

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Admin_Access_OtherCostPackages extends AppCompatActivity {

    private static final String CHANNEL_ID ="A002" ;
    Button signup;
    EditText entername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__access__other_cost_packages);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            //Register the channel with the system;you can't change the importance
            //or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        //Hooks
        signup = (Button)findViewById(R.id.adminsing_othercost_btn);
        entername = (EditText) findViewById(R.id.adminName_forothercost);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gname = entername.getText().toString();


                final String message = "Hello! " + gname +  " Welcome to Elephas App.Only Admins can add new Other Cost details.Others Can not Add data.Only Admins are allowed to add data.If you are admin, you can access the Other Cost details page by clicking this notification.";
                //Create an explict intent for an Activity in your app

                Intent intent = new Intent(Admin_Access_OtherCostPackages.this,Add_New_Cost.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(Admin_Access_OtherCostPackages.this,0,intent,0);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Admin_Access_OtherCostPackages.this,CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("My notification").setContentText(message).setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent).setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Admin_Access_OtherCostPackages.this);

                // notificationId is a uniqu int for each notification that you must define

                notificationManager.notify(0,builder.build());

            }
        });


    }
}
