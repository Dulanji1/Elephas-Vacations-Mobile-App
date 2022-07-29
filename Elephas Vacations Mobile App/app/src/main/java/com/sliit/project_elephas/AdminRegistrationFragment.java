package com.sliit.project_elephas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdminRegistrationFragment extends Fragment {

    private static final String TAG = "AdminRegistrationFragment";
    DatabaseHelper mDatabaseHelper;
    EditText Name,NIC,PassPortNo,Email,Phone,Password,RePassword,AdminPrivilegedPassword;

    /* ADMIN_PRIVILEGED_PASSWORD is hard-coded here*/
    public static final String ADMIN_PRIVILEGED_PASSWORD = "elephas2020";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_admin_registration, container, false);

        View view = inflater.inflate(R.layout.fragment_admin_registration,
                container, false);
        Button btnRegisterAdmin = (Button) view.findViewById(R.id.btnRegisterAdmin);
        mDatabaseHelper = new DatabaseHelper(getContext());

        Name = (EditText) view.findViewById(R.id.etAdminName);
        NIC = (EditText) view.findViewById(R.id.etAdminNIC);
        PassPortNo = (EditText) view.findViewById(R.id.etAdminPassPort);
        Email = (EditText) view.findViewById(R.id.etAdminEmail);
        Phone = (EditText) view.findViewById(R.id.etAdminPhone);
        Password = (EditText) view.findViewById(R.id.etAdminPassword);
        RePassword = (EditText) view.findViewById(R.id.etAdminRePassword);
        AdminPrivilegedPassword = (EditText) view.findViewById(R.id.etAdminPrivilegedPassword);

        btnRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //on button click
                String name = Name.getText().toString().trim();
                String nic = NIC.getText().toString().trim();
                String passportno = PassPortNo.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String phone = Phone.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String repassword = RePassword.getText().toString().trim();
                String adminprivilegedpassword = AdminPrivilegedPassword.getText().toString().trim();


                //check all the fields are not empty
                if (name.length() != 0 && nic.length() != 0 && passportno.length() != 0 && phone.length() != 0 && password.length() != 0 && repassword.length() != 0 && adminprivilegedpassword.length() != 0) {

                    //check password == re-typed password
                    if (password.equals(repassword)) {

                        if (adminprivilegedpassword.equals(ADMIN_PRIVILEGED_PASSWORD)) {

                            //data insertion happens here
                            //if data insertion was successful, isInserted == true, else false
                            boolean isInserted = mDatabaseHelper.insertAdminData(name, nic, passportno,email, phone, password);

                            if (isInserted == true) {
                                //data insertion successful
                                Toast.makeText(getContext(),"Data Inserted Successfully! : Admin Table",Toast.LENGTH_SHORT).show(); //display message to display successful data insertion

                                //resetForm();    //after successful data insertion all form fields should be empty
                                Name.setText("");
                                NIC.setText("");
                                PassPortNo.setText("");
                                Email.setText("");
                                Phone.setText("");
                                Password.setText("");
                                RePassword.setText("");
                                AdminPrivilegedPassword.setText("");

                                //notification

                            } else {
                                //something went wrong when data is inserted
                                Toast.makeText(getContext(),"Error: Data Not Inserted! : Admin Table",Toast.LENGTH_SHORT).show(); //display message
                            }
                        } else  {
                            //display that Admin password mismatched / invalid
                            Toast.makeText(getContext(),"Error: Admin Privileged Password invalid !",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        //Should display here that password != re-typed password
                        Toast.makeText(getContext(),"Error: Password and Re-Typed Password mismatched!",Toast.LENGTH_SHORT).show();
                    }


                } else {
                    //if some fields are empty then display all are required
                    Toast.makeText(getContext(),"All fields are required!",Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(v.getContext(),"Admin Registered Successful!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
