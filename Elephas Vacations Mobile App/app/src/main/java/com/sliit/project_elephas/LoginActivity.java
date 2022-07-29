package com.sliit.project_elephas;

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
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "ChannelOne";
    DatabaseHelper mDatabaseHelper;
    EditText UserOrAdmin,Password;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserOrAdmin = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());


        //notification
        //channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);     // Register the channel with the system; you can't change the importance     // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the values from the EditText fields in the LoginActivity
                String userOrAdmin = UserOrAdmin.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (userOrAdmin.length() != 0 && password.length() != 0) {
                    findOutCustomerOrAdmin(userOrAdmin,password);
                } else  {
                    //if some fields are empty then display all are required
                    Toast.makeText(getApplicationContext(),"All fields are required!",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public void findOutCustomerOrAdmin(String userOrAdmin, String password) {

        Customer thisCustomer = mDatabaseHelper.getLoginCustomerCredentials(userOrAdmin, password);
        if (thisCustomer.getId() == 0) {
            Admin thisAdmin = mDatabaseHelper.getLoginAdminCredentials(userOrAdmin, password);
            if (thisAdmin.getId() == 0) {
                Toast.makeText(getApplicationContext(),"Invalid Credentials !",Toast.LENGTH_SHORT).show(); //display message
            } else  {
                //then the admin is found and it's login credentials are valid

                // notification    //Create an explicit intent for an Activity in your app
                Intent intent = new Intent(getApplicationContext(), AfterLoginActivityAdminView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("ADMIN_ID", thisAdmin.getId());
                intent.putExtra("ADMIN_PASSPORT_NO", thisAdmin.getPassportno());
                intent.putExtra("ADMIN_EMAIL", thisAdmin.getEmail());
                intent.putExtra("ADMIN_NAME", thisAdmin.getName());
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT); //flag: 0 then changed PendingIntent.FLAG_UPDATE_CURRENT, we should make it to pass values via intent

                // Toast message //alert user to check the notification
                Toast.makeText(getApplicationContext(),"Welcome! Please click on the notification",Toast.LENGTH_SHORT).show(); //display message

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID).setSmallIcon(R.drawable.elephas_icon).setContentTitle("Welcome!").setContentText("Hello! "+ thisAdmin.getName() + ", Welcome to Elephas Vacation").setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent).setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(0, builder.build());


/*                Intent intent = new Intent(getApplicationContext(),AfterLoginActivityAdminView.class);
                intent.putExtra("ADMIN_ID", thisAdmin.getId());
                intent.putExtra("ADMIN_PASSPORT_NO", thisAdmin.getPassportno());
                intent.putExtra("ADMIN_EMAIL", thisAdmin.getEmail());
                intent.putExtra("ADMIN_NAME", thisAdmin.getName());
                startActivity(intent);*/
            }
        } else {
            //then the customer is found and it's login credentials are valid

            // notification    //Create an explicit intent for an Activity in your app
            Intent intent = new Intent(getApplicationContext(), AfterLoginCustomerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("CUSTOMER_ID", thisCustomer.getId());
            intent.putExtra("CUSTOMER_PASSPORT_NO", thisCustomer.getPassportno());
            intent.putExtra("CUSTOMER_EMAIL", thisCustomer.getEmail());
            intent.putExtra("CUSTOMER_NAME", thisCustomer.getName());
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT); //flag: 0 then changed PendingIntent.FLAG_UPDATE_CURRENT, we should make it to pass values via intent

            // Toast message //alert user to check the notification
            Toast.makeText(getApplicationContext(),"Welcome! Please click on the notification",Toast.LENGTH_SHORT).show(); //display message

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID).setSmallIcon(R.drawable.elephas_icon).setContentTitle("Welcome!").setContentText("Hello! "+ thisCustomer.getName() + ", Welcome to Elephas Vacation").setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent).setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
            notificationManager.notify(0, builder.build());




/*            Intent intent = new Intent(getApplicationContext(),AfterLoginCustomerActivity.class);
            intent.putExtra("CUSTOMER_ID", thisCustomer.getId());
            intent.putExtra("CUSTOMER_PASSPORT_NO", thisCustomer.getPassportno());
            intent.putExtra("CUSTOMER_EMAIL", thisCustomer.getEmail());
            intent.putExtra("CUSTOMER_NAME", thisCustomer.getName());
            //intent.putExtra("CUSTOMER_OBJECT",thisCustomer);
            startActivity(intent);*/
        }

    }


    public void redirectToRegistrationActivity(View view){

        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);

    }


}
