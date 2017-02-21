package com.example.yuzelli.diary.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yuzelli.diary.R;
import com.example.yuzelli.diary.bean.MyUser;
import com.example.yuzelli.diary.constants.ConstantUtils;
import com.example.yuzelli.diary.utils.SharePreferencesUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.http.bean.Init;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends BaseActivity {

    private EditText et_username;
    private EditText et_passWord;
    private Button btn_login;
    private Button btn_zuce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username  = (EditText) this.findViewById(R.id.et_username);
        et_passWord  = (EditText) this.findViewById(R.id.et_passWord);
        btn_login = (Button) this.findViewById(R.id.btn_login);
        btn_zuce = (Button) this.findViewById(R.id.btn_zuce);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.loginByAccount(et_username.getText().toString().trim(), et_passWord.getText().toString().trim(), new LogInListener<MyUser>() {

                    @Override
                    public void done(MyUser user, BmobException e) {
                        if(user!=null){
                            SharePreferencesUtil.saveObject(getApplication(), ConstantUtils.UserInfo_SP,user);
                            Toast.makeText(getApplication(),"登陆成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(getApplication(),"登陆失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btn_zuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ZuCeActivity.class);
                startActivity(intent);
            }
        });
    }
}
