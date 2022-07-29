package com.sliit.project_elephas;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UserRegistrationFragment extends Fragment {

    private static final String TAG = "UserRegistrationFragment";

    DatabaseHelper mDatabaseHelper;
    EditText Name,Nationality,PassPortNo,Email,Phone,Password,RePassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_user_registration, container, false);

        View view = inflater.inflate(R.layout.fragment_user_registration,
                container, false);
        Button btnRegisterUser = (Button) view.findViewById(R.id.btnRegisterUser);
        mDatabaseHelper = new DatabaseHelper(view.getContext());

        Name = (EditText) view.findViewById(R.id.etName1);
        Nationality = (EditText) view.findViewById(R.id.etNationality);
        PassPortNo = (EditText) view.findViewById(R.id.etPassPort);
        Email = (EditText) view.findViewById(R.id.etEmail);
        Phone = (EditText) view.findViewById(R.id.etPhone);
        Password = (EditText) view.findViewById(R.id.etPassword);
        RePassword = (EditText) view.findViewById(R.id.etRePassword);

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on button click
                String name = Name.getText().toString().trim();
                String nationality = Nationality.getText().toString().trim();
                String passportno = PassPortNo.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String phone = Phone.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String repassword = RePassword.getText().toString().trim();


                //check all the fields are not empty
                if (name.length() != 0 && nationality.length() != 0 && passportno.length() != 0 && phone.length() != 0 && password.length() != 0 && repassword.length() != 0) {

                    //check password and re-typed password are matched or not here
                    if (password.equals(repassword)) {

                        //data insertion happens here
                        //if data insertion was successful, isInserted == true, else false
                        boolean isInserted = mDatabaseHelper.insertCustomerData(name, nationality, passportno,email, phone, password);

                        if (isInserted == true) {
                            //data insertion successful
                            Toast.makeText(getContext(),"Data Inserted Successfully!",Toast.LENGTH_SHORT).show(); //display message to display successful data insertion

                            //resetForm();    //after successful data insertion all form fields should be empty
                            Name.setText("");
                            Nationality.setText("");
                            PassPortNo.setText("");
                            Email.setText("");
                            Phone.setText("");
                            Password.setText("");
                            RePassword.setText("");

                            //notification


                        } else {
                            //something went wrong when data is inserted
                            Toast.makeText(getContext(),"Error: Data Not Inserted! : Customer Table"+name+"#"+nationality,Toast.LENGTH_SHORT).show(); //display message
                        }

                    } else {
                        //display user that the password and re-typed password is mismatched
                        Toast.makeText(getContext(),"Error: Password and Re-Typed Password mismatched!",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //if some fields are empty then display all are required
                    Toast.makeText(getContext(),"All fields are required!",Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(v.getContext(),"User Registered Successful!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
