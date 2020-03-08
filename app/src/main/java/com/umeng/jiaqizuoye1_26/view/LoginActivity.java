package com.umeng.jiaqizuoye1_26.view;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;


import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseActivity;
import com.umeng.jiaqizuoye1_26.bean.UserBean;
import com.umeng.jiaqizuoye1_26.interfaces.login.LoginConstract;
import com.umeng.jiaqizuoye1_26.presenter.login.LoginPercenter;
import com.umeng.jiaqizuoye1_26.utils.SpUtils;


public class LoginActivity extends BaseActivity<LoginConstract.View, LoginConstract.Persenter> implements LoginConstract.View {



    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        TextView edit_username = findViewById(R.id.edit_username);
        TextView edit_password = findViewById(R.id.edit_password);
        TextView register = findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        TextView login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = edit_username.getText().toString();
                String password = edit_password.getText().toString();
                presenter.login(nickname,password);
            }
        });

    }

    @Override
    protected void initData() {
    }


    @Override
    protected LoginConstract.Persenter createPersenter() {
        return new LoginPercenter();
    }

    @Override
    public void loginReturn(UserBean result) {
        //登录成功以后存入sp
        SpUtils.getInstance().setValue("token",result.getData().getToken());
        finish();
    }

    @Override
    public void showTips(String error) {

    }
}
