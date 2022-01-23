package com.sanjeevani.adapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResetActivity extends AppCompatActivity {
    private Button resetBtn;
    TextView tvShowCategory;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

//        Get the id
        resetBtn = findViewById(R.id.resetBtn);
        tvShowCategory = findViewById(R.id.tvSelectedCategory);

//        Setting up the shared preferences
        preferences = getSharedPreferences("MyPref",MODE_PRIVATE);
        editor = preferences.edit();

//        Setup the textview
        tvShowCategory.setText(preferences.getString("saved_category",""));

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(ResetActivity.this,CategoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}