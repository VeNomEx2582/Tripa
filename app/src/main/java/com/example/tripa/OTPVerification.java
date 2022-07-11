package com.example.tripa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.PhantomReference;

public class OTPVerification extends AppCompatActivity {

    private EditText otpet1,otpet2,otpet3,otpet4;
    private TextView resendbtn;


    //true after every 60 seconds
    private boolean resendenabled = false;

    //resend time in seconds
    private int resendtime = 60;

    private int selectedetposition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        otpet1 = findViewById(R.id.otpet1);
        otpet2 = findViewById(R.id.otpet2);
        otpet3 = findViewById(R.id.otpet3);
        otpet4 = findViewById(R.id.otpet4);

        resendbtn = findViewById(R.id.resendbtn);

        final Button verifybtn = findViewById(R.id.verifybtn);
        final TextView otpemail = findViewById(R.id.otpemail);
        final TextView otpmobile = findViewById(R.id.otpmobile);

        final String getemail = getIntent().getStringExtra("email");
        final String getmobile = getIntent().getStringExtra("mobile");

        otpemail.setText(getemail);
        otpmobile.setText(getmobile);

        otpet1.addTextChangedListener(textWatcher);
        otpet2.addTextChangedListener(textWatcher);
        otpet3.addTextChangedListener(textWatcher);
        otpet4.addTextChangedListener(textWatcher);


        //default open keboard at otpet1
        showkeyboard(otpet1);

        //start resend countdown timer
        startcountdowntimer();

        resendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(resendenabled){

                    //hendle your resend code here

                    //start resend count down timer
                    startcountdowntimer();
                }
            }
        });

        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String generateotp = otpet1.getText().toString()+otpet2.getText().toString()+otpet3.getText().toString()+otpet4.getText().toString();

                if(generateotp.length() == 4){

                    //hendele your otp verification here

                }
                startActivity(new Intent(OTPVerification.this,Drawer_screen.class));

            }
        });

    }
    private void showkeyboard(EditText otpet){
        otpet.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpet, InputMethodManager.SHOW_IMPLICIT);
    }

    private void startcountdowntimer(){
        resendenabled = false;
        resendbtn.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resendtime * 1000, 1000){

            @Override
            public void onTick(long millissUntilFinished) {

                resendbtn.setText("Resend Code ("+(millissUntilFinished / 1000)+")");
            }

            @Override
            public void onFinish() {

                resendenabled = true;
                resendbtn.setText("Resend Code");
                resendbtn.setTextColor(getResources().getColor(R.color.primary));


            }
        }.start();
    }
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {


            if(s.length() > 0){

                if(selectedetposition == 0){
                    selectedetposition = 1;
                    showkeyboard(otpet2);

                }
                else if(selectedetposition == 1){
                    selectedetposition = 2;
                    showkeyboard(otpet3);

                }
                else if(selectedetposition == 2){
                    selectedetposition = 3;
                    showkeyboard(otpet4);

                }

            }

        }
    };




    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_DEL){

            if(selectedetposition == 3){
                selectedetposition = 2;
                showkeyboard(otpet3);
            }
            else if(selectedetposition == 2){
                selectedetposition =1;
                showkeyboard(otpet2);

            }

            else if(selectedetposition == 1){
                selectedetposition = 0;
                showkeyboard(otpet1);

            }

            return true;
        }
        else {
            return super.onKeyUp(keyCode, event);
        }

    }

}