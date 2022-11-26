package com.example.login_sharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;

    private boolean mlogin = false;
    public static SharedPreferences mSharedPref;
    private final String sharedPrefFile = "com.example.login_sharedpreferences";

    public static final String COUNT_KEY = "Key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        mSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mlogin = mSharedPref.getBoolean(COUNT_KEY, false);

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        if (mlogin == true){
            startActivity(intent);
        }

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usName = username.getText().toString();
                String pwd = password.getText().toString();

                if(usName.equals("admin") && pwd.equals("admin123")){
                    mlogin = true;
                    saveLogin();
                    startActivity(intent);
                } else {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setTitle("Username atau password salah!");
                    alertBuilder.setMessage("Silahkan masukan username dan password yang benar!");

                    alertBuilder.setNeutralButton("Ok", (dialog, which) ->{
                        Toast.makeText(getApplicationContext(), "Masukan username dan password anda kembali", Toast.LENGTH_LONG).show();

                    });

                    alertBuilder.show();

                    username.setText("");
                    password.setText("");
                }
            }
        });
    }

    private void saveLogin(){
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(COUNT_KEY, mlogin);
        editor.apply();
    }

    public static void logout(){
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(COUNT_KEY, false);
        editor.apply();
    }
}