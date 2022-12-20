package com.example.tugas5_recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tugas5_recycleview.databinding.ActivityLoginBinding;
import com.example.tugas5_recycleview.MyDbHelper;
import com.example.tugas5_recycleview.SessionManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    MyDbHelper MyDbHelper;
    private ActivityLoginBinding binding;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity1.class);
            startActivity(intent);
            finish();
        }MyDbHelper = new MyDbHelper(this);
        SQLiteDatabase sqLiteDatabase = MyDbHelper.getWritableDatabase();
        binding.Signinbuttonid.setOnClickListener(this);
        binding.SignUpHerebuttonid.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String username = binding.signinusernameEditText.getText().toString();
        String password = binding.signinpasswordEditText.getText().toString();
        if (v.getId() == R.id.Signinbuttonid) {
            Boolean result = MyDbHelper.findPassword(username, password);
            if (result == true) {
                Intent intent = new Intent(LoginActivity.this, MainActivity1.class);
                startActivity(intent);
                session.setLogin(true);
                finish();
            } else {
                Toast.makeText(this, "username and password didn`t match", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.SignUpHerebuttonid) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}