package com.example.connectivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 设置资料
 * Created by 杨航 on 2016/10/30.
 */
public class Setting_information extends Activity {

    public Setting_information(){
        Myvalues myvalues = new Myvalues();
        myvalues.setSetting_information(this);
    }

    Validate_phone vphone;

    public void setVphone(Validate_phone vphone) {
        this.vphone = vphone;
    }

    private EditText e1, e2;
    /**
     * 注册的账号密码
     */
    private String Susername, Spassword;

    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_information);
        e1 = (EditText) findViewById(R.id.setting_information_username1);
        e2 = (EditText) findViewById(R.id.setting_information_password1);
        MyTopbar mytopbar = (MyTopbar) findViewById(R.id.setting_information_topbar);
        button = (Button) findViewById(R.id.setting_information_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Susername = e1.getText().toString().trim();
                Spassword = e2.getText().toString().trim();
                if (Spassword.length() == 0 || Susername.length() == 0) {
                    Toast.makeText(Setting_information.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (Spassword.length() <= 6) {
                    Toast.makeText(Setting_information.this, "密码长度不能小于七位", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent().setClass(Setting_information.this, Registered_success.class));
                    finish();
                }
            }
        });
        mytopbar.setOnTopbarClickListener(new MyTopbar.topbarClickListener() {
            @Override
            public void leftClick() {
                startActivity(new Intent().setClass(Setting_information.this, Validate_phone.class));
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        mytopbar.setRightIsVisable(false);
    }

    /**
     * 返回用户注册后的信息
     * @param number
     * @return
     */
    //       1     返回用户名
    //       2     返回密码
    //   其他数字  返回电话号
    public String getinformation(int number) {
        if (number == 1) {
            return Susername;
        } else if (number == 2) {
            return Spassword;
        } else {
            return vphone.getPhoneNUmber();
        }
    }

}
