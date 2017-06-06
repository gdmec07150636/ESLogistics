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

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText register_username, register_phone, register_code, register_pwd, register_pwd_re;
    Button register_btn, login_weChat, register_question, get_valid_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

    }

    protected void initViews() {
        register_username = (EditText) findViewById(R.id.register_username);
        register_phone = (EditText) findViewById(R.id.register_phone);
        register_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(AccountValidatorUtil.isMobile(s.toString())){
                    get_valid_code.setEnabled(true);
                }else{
                    get_valid_code.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        register_code = (EditText) findViewById(R.id.register_code);
        register_pwd = (EditText) findViewById(R.id.register_pwd);
        register_pwd_re = (EditText) findViewById(R.id.register_pwd_re);
        register_btn = (Button) findViewById(R.id.register_btn);
        login_weChat = (Button) findViewById(R.id.login_weChat);
        get_valid_code = (Button) findViewById(R.id.get_valid_code);
        get_valid_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("16516", "onClick: ");
                new getValidCode().start();
            }
        });
        register_question = (Button) findViewById(R.id.register_question);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = checkForm();
                if (!"AllPassed".endsWith(s)) {
                    Tools.ShowLongToast(getApplicationContext(),s);
                    Log.d("onClick", "onClick: " + s);
                }else{
                    new sendRequest().start();
                }
            }
        });
        login_weChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.ShowShortToast(v.getContext(), "此功能有待开发");
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
                Tools.ShowShortToast(v.getContext(), "此功能有待开发");
            }
        });

    }

    private String checkForm() {
        String username = register_username.getText().toString();
        if (!AccountValidatorUtil.isUsername(username)) return "用户名不合法";
        String phone = register_phone.getText().toString();
        if (!AccountValidatorUtil.isMobile(phone)) return "手机号不合法";
        String password = register_pwd.getText().toString();
        if (!AccountValidatorUtil.isPassword(password)) return "密码不合法";
        String repassword = register_pwd_re.getText().toString();
        if (!repassword.equals(password)) return "两次密码不一致";
        return "AllPassed";
    }

    class sendRequest extends Thread {
        @Override
        public void run() {
            super.run();
            String username = register_username.getText().toString();
            String phone = register_phone.getText().toString();
            String pwd = register_pwd.getText().toString();
            String valid = register_code.getText().toString();
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder().add("username", username).add("valid",valid).add("phone", phone).add("password", pwd).build();
            Request request = new Request.Builder().url("http://10.0.2.2/tt/aa.php").post(body).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String r = response.body().string();
                Log.d("asdasd", "run: "+r);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private class getValidCode extends Thread {
        public void run() {
            super.run();
            Log.d("Valid", "run: ");
            String phone = register_phone.getText().toString();
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder().add("phone", phone).build();
            Request request = new Request.Builder().url("http://10.0.2.2/tt/aa.php").post(body).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String r = response.body().string();
                Log.d("Valid", "run: "+r);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
