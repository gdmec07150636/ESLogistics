package com.example.rynfar.eslogistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class OrderInfoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        toolbar = (Toolbar) findViewById(R.id.order_info_toolbarr);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        toolbar.setTitle(R.string.order_info);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
