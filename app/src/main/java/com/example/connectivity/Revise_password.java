package com.example.connectivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 修改密码
 * Created by 杨航 on 2016/11/5.
 */
public class Revise_password extends Activity {

    public Revise_password(){
        Myvalues myvalues=new Myvalues();
        myvalues.setRevise_password(this);
    }

    Validate_login vlogin;

    public void setVlogin(Validate_login vlogin) {
        this.vlogin = vlogin;
    }

    private EditText e1, e2;
    /**
     * 修改后的密码
     */
    private String Rpassword1, Rpassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revise_password);
        e1 = (EditText) findViewById(R.id.revise_password_new1);
        e2 = (EditText) findViewById(R.id.revise_password_confirm1);
        Rpassword1 = e1.getText().toString().trim();
        Rpassword2 = e2.getText().toString().trim();
    }

    public void onClick() {
        if (Rpassword1 == Rpassword2) {
            startActivity(new Intent(this, Login.class));
            finish();
        } else {
            Toast.makeText(Revise_password.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
            e2.requestFocus();
        }
    }

    /**
     * 获取修改后电话号与其相对应的密码
     *
     * @param number
     * @return
     */
    //      1         返回修改后的密码
    //   其他数字     返回与其相对应的电话号码
    public String getinformation(int number){
        if(number ==1){
            return Rpassword2;
        }else{
            return vlogin.getPhoneNUmber();
        }
    }
}
