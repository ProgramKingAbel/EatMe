package com.example.grace.eatme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LogIn extends AppCompatActivity {

    EditText phone,password;
    Button logIn;
    ProgressDialog progressDialog;
    private CheckBox checkBoxRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

       phone = (EditText) findViewById(R.id.edtPhone_number);
        password = (EditText) findViewById(R.id.edtPass);
        logIn = (Button) findViewById(R.id.logedIn);
        progressDialog = new ProgressDialog(this);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");




        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String edt_phone = phone.getText().toString().trim();
                String edt_pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(edt_phone)) {

                    Toast.makeText(LogIn.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(edt_pass)) {
                    Toast.makeText(LogIn.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (checkBoxRememberMe.isChecked()) {
                    saveLoginDetails(edt_phone, edt_pass);
                    startHomeActivity();
                }

                final ProgressDialog mDialog = new ProgressDialog(LogIn.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //to check if user does not exist in database

                        if(dataSnapshot.child(phone.getText().toString()).exists()) {

                            mDialog.dismiss();

                            User user = dataSnapshot.child(phone.getText().toString()).getValue(User.class);
                            user.setPhone(phone.getText().toString());


                            if (user.getPassword().equals(password.getText().toString())) {
                                //Toast.makeText(LogIn.this, "Log In successful", Toast.LENGTH_LONG).show();
                                Intent home = new Intent(LogIn.this,MenuActivity.class);
                                Current.currentUser= user;
                                startActivity(home);
                                finish();

                            }
                            else {
                                Toast.makeText(LogIn.this, "Log in Failed !!", Toast.LENGTH_LONG).show();
                            }

                        }else
                            {
                                mDialog.dismiss();
                                Toast.makeText(LogIn.this,"User Does Not Exist In Database",Toast.LENGTH_LONG).show();

                            }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

                checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBoxRememberMe);
                if (!new PrefManager(this).isUserLogedOut()) {

                    startHomeActivity();
                }


            }

            private void startHomeActivity() {
                Intent intent = new Intent(LogIn.this, MenuActivity.class);
                startActivity(intent);

            }

            private void saveLoginDetails(String phone, String password) {
                new PrefManager(this).saveLoginDetails(phone, password);
            }
        }


