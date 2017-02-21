package com.example.yuzelli.diary.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yuzelli.diary.R;
import com.example.yuzelli.diary.bean.Diary;
import com.example.yuzelli.diary.constants.ConstantUtils;
import com.example.yuzelli.diary.utils.CommonAdapter;
import com.example.yuzelli.diary.utils.SharePreferencesUtil;
import com.example.yuzelli.diary.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FindActivity extends BaseActivity {
    private ImageView img_back;
    private EditText et_userInput;
    private TextView tv_find;
    private List<Diary> diaryList;
    private List<Diary> findList;
    private CommonAdapter<Diary> adapter;
    private ListView lv_diary;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        img_back = (ImageView) this.findViewById(R.id.img_back);
        et_userInput = (EditText) this.findViewById(R.id.et_userInput);
        tv_find = (TextView) this.findViewById(R.id.tv_find);
        lv_diary = (ListView) this.findViewById(R.id.lv_diary);
        tv_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findDiary();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        context = this;

    }

    /**
     * 找到日记
     */
    private void findDiary() {
        String title = et_userInput.getText().toString().trim();
        diaryList = (List<Diary>) SharePreferencesUtil.readObject(getApplication(), ConstantUtils.DIARY_SP);
        findList = new ArrayList<>();
        for (Diary diary : diaryList) {
            if (diary.getTitle().equals(title)) {
                findList.add(diary);
            }
        }

        updateListView();

    }

    private void updateListView() {
        adapter = new CommonAdapter<Diary>(getApplication(), findList, R.layout.item_dairy_content) {
            @Override
            public void convert(ViewHolder helper, Diary item) {
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_time, item.getTime());
                helper.setText(R.id.tv_weather, item.getWeather());
            }
        };
        lv_diary.setAdapter(adapter);
        lv_diary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaryContentActivity.actionState(context,findList.get(position));
            }
        });
    }

}
