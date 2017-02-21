package com.example.yuzelli.diary.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.diary.R;
import com.example.yuzelli.diary.bean.Diary;

public class DiaryContentActivity extends BaseActivity {
    private ImageView img_back;
    private TextView tv_title;
    private TextView tv_time;
    private TextView tv_weather;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_content);
        Intent intent = getIntent();
        Diary diary = (Diary) intent.getSerializableExtra("diary");
        img_back = (ImageView) this.findViewById(R.id.img_back);
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        tv_time = (TextView) this.findViewById(R.id.tv_time);
        tv_weather = (TextView) this.findViewById(R.id.tv_weather);
        tv_content = (TextView) this.findViewById(R.id.tv_content);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_title.setText(diary.getTitle());
        tv_time.setText(diary.getTime());
        tv_weather.setText(diary.getWeather());
        tv_content.setText(diary.getContnent());



    }

    public static void actionState(Context context, Diary diary){
        Intent intent = new Intent(context,DiaryContentActivity.class);
        intent.putExtra("diary",diary);
        context.startActivity(intent);

    }
}
