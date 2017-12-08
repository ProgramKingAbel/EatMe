package com.example.grace.eatme;

/**
 * Created by Grace on 12/7/2017.
 */

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity{
    public String getUid() {

        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
