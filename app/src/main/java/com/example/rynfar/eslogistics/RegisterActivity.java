package com.example.rynfar.eslogistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.rynfar.eslogistics.tools.Tools;

import net.qiujuer.genius.ui.widget.Button;

public class RegisterActivity extends AppCompatActivity {
    EditText register_username, register_phone, register_code, register_pwd, register_pwd_re;
    Button login_btn, login_weChat, register_question,get_valid_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

    }

    protected void initViews(){
        register_username =(EditText) findViewById(R.id.register_username);
        register_phone =(EditText) findViewById(R.id.register_phone);
        register_code =(EditText) findViewById(R.id.register_code);
        register_pwd = (EditText)findViewById(R.id.register_pwd);
        register_pwd_re = (EditText) findViewById(R.id.register_pwd_re);
        login_btn = (Button)findViewById(R.id.login_btn );
        login_weChat = (Button)findViewById(R.id.login_weChat );
        get_valid_code = (Button)findViewById(R.id.get_valid_code);
        register_question = (Button)findViewById(R.id.register_question );
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        login_weChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.ShowShotToast(v.getContext(),"此功能有待开发");
                Log.d("aaa", "onClick: 此功能有待开发");
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
                Log.d("aaa", "onClick: 此功能有待开发");
            }
        });

    }

}
