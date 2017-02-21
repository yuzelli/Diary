package com.example.yuzelli.diary.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.yuzelli.diary.R;


/**
 *Created by Administrator on 2016/12/3.
 * 配置baseActivity
 * @author 李秉龙
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
