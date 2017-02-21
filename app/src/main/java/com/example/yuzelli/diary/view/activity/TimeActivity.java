package com.example.yuzelli.diary.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.yuzelli.diary.R;
import com.example.yuzelli.diary.bean.Diary;
import com.example.yuzelli.diary.constants.ConstantUtils;
import com.example.yuzelli.diary.utils.CommonAdapter;
import com.example.yuzelli.diary.utils.SharePreferencesUtil;
import com.example.yuzelli.diary.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TimeActivity extends BaseActivity {
    private ImageView img_back;
    private List<Diary> diaryList;
    private ListView lv_diary;
    private CommonAdapter<Diary> adapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        img_back = (ImageView) this.findViewById(R.id.img_back);
        lv_diary = (ListView) this.findViewById(R.id.lv_diary);
context =this;
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        diaryList = (List<Diary>) SharePreferencesUtil.readObject(getApplication(), ConstantUtils.DIARY_SP);
        if (diaryList==null){
            diaryList = new ArrayList<>();
        }
        adapter = new CommonAdapter<Diary>(getApplication(),diaryList,R.layout.item_diary_time) {
            @Override
            public void convert(ViewHolder helper, Diary item) {
                helper.setText(R.id.tv_time,item.getTime());
                helper.setText(R.id.tv_title,item.getTitle());
                helper.setText(R.id.tv_weather,item.getWeather());
                helper.setText(R.id.tv_content,item.getContnent());
            }
        };
        lv_diary.setAdapter(adapter);
        lv_diary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaryContentActivity.actionState(context,diaryList.get(position));
            }
        });
    }
}
