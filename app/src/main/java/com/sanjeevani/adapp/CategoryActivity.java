package com.sanjeevani.adapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.sanjeevani.adapp.utils.SessionManager;

public class CategoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String PREFS_NAME = "PrefsFile";

    private Button selectBtn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private String selected_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        Finding the id of the widgets
        Spinner spinner = (Spinner) findViewById(R.id.state_spinner);
        selectBtn = findViewById(R.id.selectBtn);

//        Setting up the preferences
//        preferences = getSharedPreferences("MyPref",MODE_PRIVATE);
//        editor = preferences.edit();

//        check if a category is already selected
//        if(preferences.contains("saved_category")){
//            Intent intent = new Intent(CategoryActivity.this,MainActivity.class);
//            startActivity(intent);
//        }
        SessionManager sessionManager=new SessionManager(CategoryActivity.this);
        boolean isloggedin=sessionManager.checkLogin();
        if(isloggedin){
            startActivity(new Intent(CategoryActivity.this, MainActivity.class));
            finish();
        }else{
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.state_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);

//        Setting up the button for selecting the State
            selectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    editor.putString("saved_category",selected_choice);
//                    editor.commit();
                    SessionManager sessionManager=new SessionManager(CategoryActivity.this);
                    sessionManager.createloginsession(selected_choice);
                    Intent intent = new Intent(CategoryActivity.this,ResetActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        selected_choice = choice;
        Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}