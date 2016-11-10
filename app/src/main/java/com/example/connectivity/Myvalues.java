package com.example.connectivity;

/**
 * Created by 杨航 on 2016/11/8.
 */
public class Myvalues {
    Validate_login validate_login;
    Login login;
    Revise_password revise_password;
    Setting_information setting_information;

    public void setValidate_login(Validate_login validate_login) {
        this.validate_login = validate_login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setRevise_password(Revise_password revise_password) {
        this.revise_password = revise_password;
    }

    public void setSetting_information(Setting_information setting_information) {
        this.setting_information = setting_information;
    }
    /**
     * 我在这说一下我在本Java 的命名规则
     * 方法的名字通常为"get+什么操作_你需要的值"
     * 例如想要得到在注册时候的账号
     * 你可以写  getRegisered_Username()
     * 这里注意除了get其他的单词单元首字母大写
     *               如果有需求再向我提
     *               稳妥
     */

    /**
     * 用户第一次注册时候的信息
     *
     * @return
     */
    //获得用户注册填写的账号
    public String getRegistered_Username() {
        return setting_information.getinformation(1);
    }

    //获得用户注册填写的密码
    public String getRegistered_Password() {
        return setting_information.getinformation(2);
    }

    //获得用户注册填写的电话号
    public String getRegistered_Phonenumber() {
        return setting_information.getinformation(3);
    }


    /**
     * 用户修改密码时接收的数据
     *
     * @return
     */
    //获得用户修改后的密码
    public String getRevise_Password() {
        return revise_password.getinformation(1);
    }

    //获得用户修改后的密码所对应的电话号
    public String getRevise_Phonenumber() {
        return revise_password.getinformation(2);
    }


    /**
     * 用户登陆时的信息
     *
     * @return
     */
    //获得用户用账号密码登录的账号
    public String getLogin_Username() {
        return login.getinformation(1);
    }

    //获得用户用账号密码登录的密码
    public String getLogin_Password() {
        return login.getinformation(2);
    }

    /**
     * 用户在用手机号验证登录时的信息
     */
    public String getValidate_Phonenumber() {
        return validate_login.getPhoneNUmber();
    }
}
