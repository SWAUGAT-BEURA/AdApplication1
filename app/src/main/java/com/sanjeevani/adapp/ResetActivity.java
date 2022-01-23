package com.sanjeevani.adapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sanjeevani.adapp.utils.SessionManager;

public class ResetActivity extends AppCompatActivity {
    private Button resetBtn;
    TextView tvShowCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

//        Get the id
        resetBtn = findViewById(R.id.resetBtn);
        tvShowCategory = findViewById(R.id.tvSelectedCategory);

//        Setting up the shared preferences
//        preferences = getSharedPreferences("MyPref",MODE_PRIVATE);
//        editor = preferences.edit();

        SessionManager sessionManager=new SessionManager(getApplicationContext());
        String statename=sessionManager.getState();

//        Setup the textview
        tvShowCategory.setText(statename);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(getApplicationContext()).setTitle("Reset")
                        .setMessage("Are you sure you want to Reset")
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SessionManager sessionManager=new SessionManager(getApplicationContext());
                                sessionManager.logoutuserfromsession();
                                startActivity(new Intent(getApplicationContext(), ResetActivity.class));
                                finish();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
                Intent intent = new Intent(ResetActivity.this,CategoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void submitstate(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}