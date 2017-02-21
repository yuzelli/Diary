package com.example.yuzelli.diary.view.activity;

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
import cn.bmob.v3.listener.SaveListener;

public class ZuCeActivity extends BaseActivity {
    private EditText et_username;
    private EditText et_passWord;
    private Button btn_zuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_ce);
        et_username  = (EditText) this.findViewById(R.id.et_username);
        et_passWord  = (EditText) this.findViewById(R.id.et_passWord);
        btn_zuce = (Button) this.findViewById(R.id.btn_zuce);
        btn_zuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bu = new BmobUser();
                bu.setUsername(et_username.getText().toString().trim());
                bu.setPassword(et_passWord.getText().toString().trim());

//注意：不能用save方法进行注册
                bu.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser s, BmobException e) {
                        if(e==null){
                            SharePreferencesUtil.saveObject(getApplication(), ConstantUtils.UserInfo_SP,s);
                            Toast.makeText(getApplication(),"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplication(),"注册失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}
