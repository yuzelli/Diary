package com.example.yuzelli.diary.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yuzelli.diary.R;
import com.example.yuzelli.diary.bean.MyUser;
import com.example.yuzelli.diary.constants.ConstantUtils;
import com.example.yuzelli.diary.utils.SharePreferencesUtil;

public class LockActivity extends BaseActivity {
    private View v_open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        v_open = this.findViewById(R.id.v_open);
        v_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser myUser = (MyUser) SharePreferencesUtil.readObject(getApplication(), ConstantUtils.UserInfo_SP);
                if (myUser!=null){
                    Intent intent = new Intent(LockActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(LockActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
