package com.example.tripa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private boolean passwordshowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameet = findViewById(R.id.usernameet);
        final EditText passwordet = findViewById(R.id.passwordet);
        final ImageView passwordpng = findViewById(R.id.passwordpng);
        final TextView signupbtn = findViewById(R.id.signupbtn);

        passwordpng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordshowing){
                    passwordshowing = false;

                    passwordet.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordpng.setImageResource(R.drawable.pass);
                }
                else {
                    passwordshowing = true;

                    passwordet.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordpng.setImageResource(R.drawable.password_show);
                }

                passwordet.setSelection(passwordet.length());


            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });

    }
}