package com.example.yuzelli.diary.view.activity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.yuzelli.diary.R;
import com.example.yuzelli.diary.bean.HeWeather;
import com.example.yuzelli.diary.constants.ConstantUtils;
import com.example.yuzelli.diary.utils.OkHttpClientManager;
import com.example.yuzelli.diary.utils.SharePreferencesUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.bmob.v3.Bmob;
import okhttp3.Request;

/**
 * 闪屏页，判断用户是否第一次登录
 * @author 李秉龙
 */
public class SplashActivity extends BaseActivity {
    private boolean firstUse;
    private Context context;
    private ImageView iv_spl_background;
    private static final int ANIMATION_DURATION = 3000;
    private static final float SCALE_END = 1.13F;
    private static final int[] SPLASH_ARRAY = {
            R .drawable.splash0,
            R.drawable.splash1,
            R.drawable.splash2,
            R.drawable.splash3,
            R.drawable.splash4,
            R.drawable.splash5,
            R.drawable.splash6,
            R.drawable.splash7,
            R.drawable.splash8,
            R.drawable.splash9,
            R.drawable.splash10,
            R.drawable.splash11,
            R.drawable.splash12,
            R.drawable.splash13,
            R.drawable.splash14,
            R.drawable.splash15,
            R.drawable.splash16,
    };
    private HeWeather weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv_spl_background = (ImageView) this.findViewById(R.id.iv_spl_background);
        Random r = new Random(SystemClock.elapsedRealtime());
        iv_spl_background.setImageResource(SPLASH_ARRAY[r.nextInt(SPLASH_ARRAY.length)]);
        context = SplashActivity.this;
        firstUse = getSharedPreferences("phoneHelperShared",MODE_PRIVATE).getBoolean("firstUse",true);
        animateImage();
        Bmob.initialize(this, "e58846ed3ddeb2391e79e85667a70515");
        doRequestData("北京");
    }
    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(iv_spl_background, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(iv_spl_background, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // handler.sendEmptyMessageDelayed(ConstantUtils.START_ACTIVITY, 3000);

            }
        });

    }

    private void doRequestData(String cityName) {
        OkHttpClientManager manager = OkHttpClientManager.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("city", cityName);
        map.put("key", ConstantUtils.HEFENGWEATHER_KEY);
        String url = OkHttpClientManager.attachHttpGetParams(ConstantUtils.HEFENGWEATHER_URL, map);
        manager.getAsync(url, new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                // 将{}中视为是json对象
                JSONObject jsonObject = new JSONObject(result);
                // 获取键"weatherinfo"中对应的值
                JSONArray jsonArray1 = jsonObject
                        .getJSONArray("HeWeather data service 3.0");
                JSONObject jsonObject2 = jsonArray1.getJSONObject(0);
                // 使用jar包解析数据
                Gson gson = new Gson();
                weather = gson.fromJson(
                        jsonObject2.toString(), HeWeather.class);
                handler.sendEmptyMessage(ConstantUtils.WEATHERPAGEFRRAGMENT_GET_DATA);
            }
        });
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantUtils.GUIDE_START_ACTIVITY:
                    if (!firstUse) {
                        startActivity(new Intent(SplashActivity.this, LockActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    }
                    finish();
                    break;
                case ConstantUtils.WEATHERPAGEFRRAGMENT_GET_DATA:
                    SharePreferencesUtil.saveObject(getApplication(),ConstantUtils.WEATHERPAGEFRRAGMENT,weather);
                    handler.sendEmptyMessage(ConstantUtils.GUIDE_START_ACTIVITY);
                    break;
            }
        }
    };


}