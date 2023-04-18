package com.example.login_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password,repassword;
    Button regsiter,sign_up;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.edit_username1);
        password=findViewById(R.id.edit_password1);
        repassword=findViewById(R.id.edit_repassword);
        regsiter=findViewById(R.id.sign_in_btn);
        sign_up=findViewById(R.id.sign_up_btn1);
        DB=new DBHelper(this);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all Fields", Toast.LENGTH_SHORT).show();

                }else {
                    if (pass.equals(repass)){
                        Boolean checkuser=DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert=DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registered Successfuly", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(getApplicationContext(),HomeActivty.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();

                            }
                        }else {
                            Toast.makeText(MainActivity.this, "User already exist! pleasesign up", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Pasword not matching", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        regsiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);

            }
        });
    }
}