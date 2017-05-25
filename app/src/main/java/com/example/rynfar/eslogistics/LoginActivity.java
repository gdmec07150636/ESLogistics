package com.example.rynfar.eslogistics;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rynfar.eslogistics.tools.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    EditText login_username, login_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    private void sendRequestWithHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder().add("idname", String.valueOf(login_username))
                            .add("password", String.valueOf(login_pwd)).build();
                    Request request = new Request.Builder().url("http://10.0.2.2/test.php").post(requestBody).build();
                    Response response = client.newCall(request).execute();
                    String r = response.body().string();
                    parsewithjsonobject(r);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parsewithjsonobject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String type = jsonObject.getString("status");
            if(type.equals("success")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this,Main.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                });
            }else{
                Tools.ShowShotToast(LoginActivity.this,"用户名或密码错误！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
