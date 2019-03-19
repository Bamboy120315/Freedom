package com.bamboy.freedom.page.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bamboy.freedom.R;
import com.bamboy.freedom.page.mix.ActMix;
import com.bamboy.freedom.page.music.ActMusic;
import com.bamboy.freedom.page.news.ActNews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        init();

    }

    public void init() {

        // 新闻列表按钮点击事件
        findViewById(R.id.btn_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActNews.class));
            }
        });

        // 音乐列表按钮点击事件
        findViewById(R.id.btn_music).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActMusic.class));
            }
        });

        // 混合列表按钮点击事件
        findViewById(R.id.btn_mix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActMix.class));
            }
        });
    }

}
