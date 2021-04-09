package com.example.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOtp extends AppCompatActivity {
    EditText inputMobile;
    Button bt_send;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendotp);
        inputMobile=findViewById(R.id.et_mobile);
        bt_send=findViewById(R.id.bt_send);
        progress=findViewById(R.id.progress);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputMobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(SendOtp.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                progress.setVisibility(View.VISIBLE);
                bt_send.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+inputMobile.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        SendOtp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progress.setVisibility(View.GONE);
                                bt_send.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progress.setVisibility(View.GONE);
                                bt_send.setVisibility(View.VISIBLE);
                                Toast.makeText(SendOtp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificatioId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progress.setVisibility(View.GONE);
                                bt_send.setVisibility(View.VISIBLE);
                                Intent intent=new Intent(SendOtp.this,VerifyOtp.class);
                                intent.putExtra("mobile",inputMobile.getText().toString());
                                intent.putExtra("verificationId",verificatioId);
                                startActivity(intent);
                            }
                        }
                );

            }
        });
    }
}