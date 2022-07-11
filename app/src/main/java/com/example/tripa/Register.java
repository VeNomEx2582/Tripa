package com.example.tripa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private boolean passwordshowing = false;
    private boolean conpasswordshowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = findViewById(R.id.emailet);
        final EditText mobile = findViewById(R.id.mobileet);

        final EditText password = findViewById(R.id.passwordet);
        final EditText conpassword = findViewById(R.id.conpasswordet);
        final ImageView passwordpng = findViewById(R.id.passwordpng);
        final ImageView conpasswordpng = findViewById(R.id.conpasswordpng);
        final AppCompatButton signupbtn = findViewById(R.id.signupbtn);
        final TextView signinbtn = findViewById(R.id.signupbtn);

        passwordpng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordshowing) {
                    passwordshowing = false;

                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordpng.setImageResource(R.drawable.pass);
                } else {
                    passwordshowing = true;

                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordpng.setImageResource(R.drawable.password_show);
                }

                password.setSelection(password.length());

            }
        });
        conpasswordpng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conpasswordshowing){
                    conpasswordshowing = false;

                    conpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    conpasswordpng.setImageResource(R.drawable.pass);
                }
                else {
                    conpasswordshowing = true;

                    conpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    conpasswordpng.setImageResource(R.drawable.password_show);
                }

                conpassword.setSelection(conpassword.length());


            }



        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String getmobileTxt = mobile.getText().toString();
                final String getemailTxt = email.getText().toString();

                Intent intent = (new Intent(Register.this, OTPVerification.class));
                intent.putExtra("mobile",getmobileTxt);
                intent.putExtra("email",getemailTxt);
                startActivity(intent);
                finish();

            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,OTPVerification.class));
//                finish();

            }
        });

    }
}