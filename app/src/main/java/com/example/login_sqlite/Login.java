package com.example.login_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username1,password1;
    Button sign_in;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username1=findViewById(R.id.edit_username1);
        password1=findViewById(R.id.edit_password1);
        sign_in=findViewById(R.id.sign_in_btn);
        DB=new DBHelper(this);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username1.getText().toString();
                String pass=password1.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(Login.this, "Please enter all Fields", Toast.LENGTH_SHORT).show();

                }else {
                    Boolean checkuserPassword=DB.checkUsernamePassword(user,pass);
                    if (checkuserPassword==true){
                        Toast.makeText(Login.this, "Sign in Successfuly", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(getApplicationContext(),HomeActivty.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this, "Invalid credentiels", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });
    }
}