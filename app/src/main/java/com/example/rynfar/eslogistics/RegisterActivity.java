package com.example.rynfar.eslogistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.rynfar.eslogistics.tools.AccountValidatorUtil;
import com.example.rynfar.eslogistics.tools.Tools;

import net.qiujuer.genius.ui.widget.Button;

public class RegisterActivity extends AppCompatActivity {
    EditText register_username, register_phone, register_code, register_pwd, register_pwd_re;
    Button register_btn, login_weChat, register_question,get_valid_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

    }

    protected void initViews(){
        register_username =(EditText) findViewById(R.id.register_username);
        register_phone =(EditText) findViewById(R.id.register_phone);
        register_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("onTextChanged", "onTextChanged: "+s.toString()+ AccountValidatorUtil.isMobile(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        register_code =(EditText) findViewById(R.id.register_code);
        register_pwd = (EditText)findViewById(R.id.register_pwd);
        register_pwd_re = (EditText) findViewById(R.id.register_pwd_re);
        register_btn = (Button)findViewById(R.id.register_btn );
        login_weChat = (Button)findViewById(R.id.login_weChat );
        get_valid_code = (Button)findViewById(R.id.get_valid_code);
        register_question = (Button)findViewById(R.id.register_question );
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForm();
                sendRequest();
            }
        });
        login_weChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.ShowShotToast(v.getContext(),"此功能有待开发");
            }
        });
        get_valid_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        register_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.ShowShotToast(v.getContext(),"此功能有待开发");
            }
        });

    }

    private void checkForm() {
    }
    private void sendRequest() {
    }


}
