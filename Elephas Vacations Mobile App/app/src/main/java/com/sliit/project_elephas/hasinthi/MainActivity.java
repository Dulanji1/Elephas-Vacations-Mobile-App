package com.sliit.project_elephas.hasinthi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.project_elephas.R;

public class MainActivity extends AppCompatActivity {

 /*   public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    DatabaseHelper mydb;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hasinthi);

/*
        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllViewHotelDetails();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        obj = (ListView)findViewById(R.id.hotel1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new OnItemClickListener(){

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(),ViewHotelDetails.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });*/
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_manu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);


        switch(item.getItemId()) {
            case R.id.hotel1:Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(),ViewHotelDetails.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }*/


}


