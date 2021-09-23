package com.bamboy.freedom.page;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bamboy.freedom.R;

public class PhotoInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_info);
        setTitle("查看大图");

        int resID = getIntent().getIntExtra("resID", 0);
        if (resID == 0) {
            return;
        }

        ImageView iv_photo = findViewById(R.id.iv_photo);
        iv_photo.setImageResource(resID);
    }
}