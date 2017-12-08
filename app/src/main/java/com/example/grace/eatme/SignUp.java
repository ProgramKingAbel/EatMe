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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignUp extends AppCompatActivity {

    public static final String TAG = "AddToDb";
    EditText edt_myName, edt_Phone, edt_Pass, edt_Email;
    Button signUp;
    private ProgressDialog progressDialog;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edt_myName = (EditText) findViewById(R.id.edtName);
        edt_Phone = (EditText) findViewById(R.id.edtPhone);
        edt_Pass = (EditText) findViewById(R.id.edtPass);
        edt_Email = (EditText) findViewById(R.id.edtEmail);
        progressDialog = new ProgressDialog(this);
        signUp = (Button) findViewById(R.id.signUp);


        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        mFirebaseInstance.getReference().setValue("Realtime Database");
//
        mFirebaseInstance.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

               // String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
             //   getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String edt_email = edt_Email.getText().toString();
                String edt_pass = edt_Pass.getText().toString();
                String edt_phone = edt_Phone.getText().toString().trim();
                String edt_name = edt_myName.getText().toString().trim();


                validateForm();

                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(edt_phone, edt_pass,edt_email,edt_name);

                } else {
                    updateUser(edt_phone, edt_pass,edt_email,edt_name);
                }



            }});
    }




    private void createUser(String edt_phone, String edt_pass, String edt_email, String edt_name) {
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        User user = new User(edt_phone, edt_pass,edt_email,edt_name);

        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }
    private void addUserChangeListener() {
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.getPhone() + ", " + user.getPassword() + ", " + user.getName()+ ", "+user.getEmail());

                // Display newly updated name and email
                // txtDetails.setText(user.name + ", " + user.email);

                // clear edit text
//
                Intent next = new Intent(SignUp.this,MenuActivity.class);
                startActivity(next);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }
        private void updateUser(String edt_phone, String edt_pass,String edt_email, String edt_name) {

            if (!TextUtils.isEmpty(edt_phone))
                mFirebaseDatabase.child(userId).child("phone").setValue(edt_phone);

            if (!TextUtils.isEmpty(edt_pass))
                mFirebaseDatabase.child(userId).child("password").setValue(edt_pass);

             if (!TextUtils.isEmpty(edt_email))
                mFirebaseDatabase.child(userId).child("Email").setValue(edt_email);

            if (!TextUtils.isEmpty(edt_name))
                mFirebaseDatabase.child(userId).child("Name").setValue(edt_name);


        }


    private boolean validateForm() {

       boolean result = true;
       if (TextUtils.isEmpty(edt_Phone.getText().toString())) {

            edt_Phone.setError("Please Enter Phone Number");
            result = false;
        } else {
            edt_Phone.setError(null);
        }
        if (TextUtils.isEmpty(edt_myName.getText().toString())) {

            edt_myName.setError("Please Enter Your Name");
            result = false;
        } else {
            edt_myName.setError(null);
        }
        if (TextUtils.isEmpty(edt_Pass.getText().toString())) {

            edt_Pass.setError("Please Enter Password");
            result = false;
        } else {
            edt_Pass.setError(null);
        }

        if (TextUtils.isEmpty(edt_Email.getText().toString())) {

            edt_Email.setError("Please Enter Email");
            result = false;
        } else {
            edt_Email.setError(null);
        }

        return result;


        }
        }











































//                if (TextUtils.isEmpty(edt_phone)) {
//
//                    Toast.makeText(SignUp.this,"Please enter Phone Number",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else if (TextUtils.isEmpty(edt_name)) {
//                   Toast.makeText(SignUp.this,"Please enter your Name",Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//                else if (TextUtils.isEmpty(edt_pass)) {
//                    Toast.makeText(SignUp.this,"Please enter Password",Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//                else if (TextUtils.isEmpty(edt_email)) {
//                    Toast.makeText(SignUp.this,"Please enter Email",Toast.LENGTH_SHORT).show();
//                    return;
//
//                }

//                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
//                mDialog.setMessage("Please Wait...");
//                mDialog.show();


//                table_user.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.child(edt_Phone.getText().toString()).exists()) {
//                            mDialog.dismiss();
//                            Toast.makeText(SignUp.this, "Phone Number Already Registered", Toast.LENGTH_LONG).show();
//                        } else {
//                            mDialog.dismiss();
//                            User user = dataSnapshot.getValue(User.class);
//                            Log.d(TAG, "User name: " + user.getName() + ", email " + user.getEmail() + ", password" + user.getPassword() + ",phone" + user.getPhone());
//                            table_user.child(edt_Phone.getText().toString()).setValue(user);
//                            Toast.makeText(SignUp.this, "Sign Up SuccessFul", Toast.LENGTH_LONG).show();
//                           finish();
//
//
//
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                        Log.w(TAG, "Failed to read value.", databaseError.toException());
//                    }
//                });


//            }
//        });
//
//    }
//


        



//
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference table_user = database.getReference("user");
//
//
//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String edt_phone= edt_Phone.getText().toString().trim();
//                String edt_name= edt_Name.getText().toString().trim();
//                String edt_pass= edt_Pass.getText().toString().trim();
//                String edt_email= edt_Email.getText().toString().trim();
//
//
//                if (TextUtils.isEmpty(edt_phone)) {
//
//                    Toast.makeText(SignUp.this,"Please enter Phone Number",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else if (TextUtils.isEmpty(edt_name)) {
//                    Toast.makeText(SignUp.this,"Please enter your Name",Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//                else if (TextUtils.isEmpty(edt_pass)) {
//                    Toast.makeText(SignUp.this,"Please enter Password",Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//                else if (TextUtils.isEmpty(edt_email)) {
//                    Toast.makeText(SignUp.this,"Please enter Email",Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//
//
//                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
//                mDialog.setMessage("Please Wait...");
//                mDialog.show();
//
//                table_user.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        if (dataSnapshot.child(edt_Phone.getText().toString()).exists()) {
//                            mDialog.dismiss();
//                            Toast.makeText(SignUp.this, "Phone Number Already Registered", Toast.LENGTH_LONG).show();
//                        } else {
//                            mDialog.dismiss();
//
//                            User user = new User(edt_Name.getText().toString(), edt_Pass.getText().toString());
//                            table_user.child(edt_Phone.getText().toString()).setValue(user);
//
//
//
//                            Toast.makeText(SignUp.this, "Sign Up SuccessFul", Toast.LENGTH_LONG).show();
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });








