package com.example.connectivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 验证手机号码
 * Created by 杨航 on 2016/10/30.
 */
public class Validate_login extends Activity {
    Login login;

    public void setLogin(Login login) {
        this.login = login;
    }

    public Validate_login(){
        Myvalues myvalues=new Myvalues();
        myvalues.setValidate_login(this);
        Revise_password rpassword = new Revise_password();
        rpassword.setVlogin(this);
    }

    private boolean huoqu = false, tijiao = false, shoujihao = false;

    private TextView textView;
    private String phoneNUmber = null, code = null;
    private EditText e1 = null, e2 = null;


    /**
     * 我的在Mob.com上的账号密码
     */
    //private String APPKEY = "1870dcb0148ae";
    //private String APPSECRET = "5802b9a474beb23e4016a18d2cf4adf1";
    private String APPKEY = "18709f577df03";
    private String APPSECRET = "78283b6c9ffcbb66c1b7bcda1d3e4a56";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validate_login);
        //初始化SDK,单例，可以多次调用；任何方法调用前，必须先初始化
        //initSDK(Context context,String appkey,String appSecrect,boolean warnOnReadContact)
        SMSSDK.initSDK(this, APPKEY, APPSECRET);

        textView = (TextView) findViewById(R.id.validate_login_submit);
        e1 = (EditText) findViewById(R.id.validate_login_number);
        e2 = (EditText) findViewById(R.id.validate_login_captcha);

        MyTopbar mytopbar = (MyTopbar) findViewById(R.id.validate_login_topbar);
        mytopbar.setOnTopbarClickListener(new MyTopbar.topbarClickListener() {
            @Override
            public void leftClick() {
                startActivity(new Intent().setClass(Validate_login.this, Login.class));
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        mytopbar.setRightIsVisable(false);
    }

    public void regist() {
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        tijiao = true;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Validate_login.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        huoqu = true;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Validate_login.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Validate_login.this, "请重新获取验证码", Toast.LENGTH_SHORT).show();
                        }
                    });
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        //注册短信回调
        SMSSDK.registerEventHandler(eh);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.validate_login_submit:
                phoneNUmber = e1.getText().toString().trim();

                //Log.i(WW, "a  " + phoneNUmber.length());

                if (phoneNUmber.length() == 11) {
                    SMSSDK.getVerificationCode("86", phoneNUmber);
                    regist();
                    timer.start();
                    shoujihao = true;
                } else {
                    Toast.makeText(Validate_login.this, "请输入合法手机号", Toast.LENGTH_SHORT).show();
                    e1.requestFocus();
                }
                break;
            case R.id.validate_login_login:
                code = e2.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", phoneNUmber, code);
                regist();
                if (shoujihao && huoqu) {
                    if (tijiao) {
                        startActivity(new Intent().setClass(Validate_login.this, Main_information.class));
                        login.finish();
                        finish();
                    } else {
                        Toast.makeText(Validate_login.this, "请检查你的验证码", Toast.LENGTH_SHORT).show();
                        code = e2.getText().toString().trim();
                    }
                }
                if (!shoujihao) {
                    Toast.makeText(Validate_login.this, "请输入手机号于其对应的验证码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.validate_login_revise:
                code = e2.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", phoneNUmber, code);
                regist();
                if (shoujihao && huoqu) {
                    if (tijiao) {
                        startActivity(new Intent().setClass(Validate_login.this, Revise_password.class));
                        login.finish();
                        finish();
                    } else {
                        Toast.makeText(Validate_login.this, "请检查你的验证码", Toast.LENGTH_SHORT).show();
                        code = e2.getText().toString().trim();
                    }
                }
                if (!shoujihao) {
                    Toast.makeText(Validate_login.this, "请输入手机号与其对应的验证码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 销毁回调接口
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    /**
     * 计时器
     */
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText((millisUntilFinished / 1000) + "秒后可以重发");
            textView.setEnabled(false);
        }

        @Override
        public void onFinish() {
            textView.setEnabled(true);
            textView.setText("重新获取验证码");
        }
    };

    public String getPhoneNUmber(){
        return phoneNUmber;
    }
}
