package com.example.yuzelli.diary.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.diary.R;
import com.example.yuzelli.diary.bean.Diary;
import com.example.yuzelli.diary.bean.HeWeather;
import com.example.yuzelli.diary.constants.ConstantUtils;
import com.example.yuzelli.diary.utils.DateUtils;
import com.example.yuzelli.diary.utils.OkHttpClientManager;
import com.example.yuzelli.diary.utils.SharePreferencesUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class WriteActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_back;
    private EditText et_diaryTitle;
    private EditText et_content;
    private TextView tv_time;
    private TextView tv_save;
    private TextView tv_weather;
    private List<Diary> dairyList;
    private HeWeather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        img_back = (ImageView) this.findViewById(R.id.img_back);
        et_content = (EditText) this.findViewById(R.id.et_content);
        et_diaryTitle = (EditText) this.findViewById(R.id.et_diaryTitle);
        tv_time = (TextView) this.findViewById(R.id.tv_time);
        tv_save = (TextView) this.findViewById(R.id.tv_save);
        tv_weather = (TextView) this.findViewById(R.id.tv_weather);
        dairyList = (List<Diary>) SharePreferencesUtil.readObject(getApplication(), ConstantUtils.DIARY_SP);
        img_back.setOnClickListener(this);
        tv_time.setText(DateUtils.CurrentTime()+"");
        //doRequestData("北京");
        weather = (HeWeather) SharePreferencesUtil.readObject(getApplication(),ConstantUtils.WEATHERPAGEFRRAGMENT);
        tv_weather.setText(weather.getNow().getCond().getTxt());
        tv_save.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_save:
                saveDairy();
                break;
        }
    }

    /**
     * 保存日记
     */
    private void saveDairy() {
        if (dairyList==null){
            dairyList = new ArrayList<>();
        }
        Diary diary = new Diary();
        String content = et_content.getText().toString().trim();
        String title = et_diaryTitle.getText().toString().trim();
        diary.setTitle(title);
        diary.setContnent(content);
        diary.setTime(tv_time.getText().toString().trim());
        diary.setWeather(tv_weather.getText().toString().trim());
        dairyList.add(diary);
        SharePreferencesUtil.saveObject(this, ConstantUtils.DIARY_SP, dairyList);
        finish();
    }
}
