package com.example.rynfar.eslogistics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rynfar.eslogistics.tools.Tools;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "6666";
    EditText login_username, login_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);/*
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);*/
        login_username = (EditText) findViewById(R.id.login_username);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        Button button = (Button) findViewById(R.id.register_btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        Button button1 = (Button) findViewById(R.id.pwd_visible);
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        login_pwd.setInputType(0);
                }
                return false;
            }
        });

        Button loginbtn = (Button) findViewById(R.id.login_btn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttp();
            }
        });


    }

    /**
     * 登录
     */
    private void sendRequestWithHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder().add("test", login_username.getText().toString())
                            .add("password", login_pwd.getText().toString()).build();
                    Request request = new Request.Builder().url(Const.BASE_URL+Const.LOGIN_SUFFIX).post(requestBody).build();
                    Response response = client.newCall(request).execute();
                    String r = response.body().string();
                    Log.d(TAG, "run: "+r);
                    parsewithjsonobject(r);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parsewithjsonobject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            //JSONObject jsonObject = jsonArray.getJSONObject(0);
            Log.d(TAG, "parsewithjsonobject: "+jsonObject.getString("status"));
            String type = jsonObject.getString("status");
            if(type.equals("success1")|type.equals("success2")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this,Main.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                });
            }else{
                Looper.prepare();
                Tools.ShowShortToast(LoginActivity.this,"用户名或密码错误！");
                Looper.loop();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
