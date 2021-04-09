package com.example.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOtp extends AppCompatActivity {
    EditText code1,code2,code3,code4,code5,code6;
    TextView tv_mobile;
    Button bt_verify;
    ProgressBar progressBar;
    String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        code1=findViewById(R.id.code_1);
        code2=findViewById(R.id.code_2);
        code3=findViewById(R.id.code_3);
        code4=findViewById(R.id.code_4);
        code5=findViewById(R.id.code_5);
        code6=findViewById(R.id.code_6);

        setUpOtp();

        tv_mobile=findViewById(R.id.tv_mobile);

        bt_verify=findViewById(R.id.bt_verify);

        progressBar=findViewById(R.id.progress);

        tv_mobile.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));

        verificationId=getIntent().getStringExtra("verificationId");

        bt_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(code1.getText().toString().trim().isEmpty()
                ||code2.getText().toString().trim().isEmpty()
                ||code3.getText().toString().trim().isEmpty()
                ||code4.getText().toString().trim().isEmpty()
                ||code5.getText().toString().trim().isEmpty()
                ||code6.getText().toString().trim().isEmpty()){
                    Toast.makeText(VerifyOtp.this, "Enter Valid Otp", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code=code1.getText().toString()+
                        code2.getText().toString()+
                        code3.getText().toString()+
                        code4.getText().toString()+
                        code5.getText().toString()+
                        code6.getText().toString();

                if(verificationId !=null){
                    progressBar.setVisibility(View.VISIBLE);
                    bt_verify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationId,code);
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                                                       @Override
                                                       public void onComplete(@NonNull Task<AuthResult> task) {
                                                           progressBar.setVisibility(View.GONE);
                                                           bt_verify.setVisibility(View.VISIBLE);
                                                           if(task.isSuccessful()){
                                                               Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                                               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                               startActivity(intent);
                                                           }
                                                           else {

                                                               Toast.makeText(VerifyOtp.this, "Verification Code Is Invalid", Toast.LENGTH_SHORT).show();
                                                           }
                                                       }
                                                   }
                            );


                }

            }


        });
    }

    private void setUpOtp(){
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}