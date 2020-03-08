package com.umeng.jiaqizuoye1_26.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseActivity;
import com.umeng.jiaqizuoye1_26.bean.RegisterBean;
import com.umeng.jiaqizuoye1_26.interfaces.register.RegisterConstract;
import com.umeng.jiaqizuoye1_26.presenter.register.RegisterPercenter;



public class RegisterActivity extends BaseActivity<RegisterConstract.View, RegisterConstract.Persenter> implements RegisterConstract.View {





    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        EditText mEditUsername = findViewById(R.id.register_username);
        EditText mEditPw1 = findViewById(R.id.register_pw1);
        EditText mEditPw2 = findViewById(R.id.register_pw2);
        Button btn = findViewById(R.id.btn_register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uses = mEditUsername.getText().toString();
                String pwd1 = mEditPw1.getText().toString();
                String pwd2 = mEditPw2.getText().toString();
                if (pwd1.length() >= 6 && pwd1.equals(pwd2)) {
                    presenter.getRegisterData(uses, pwd1);
                } else {
                    showError("你输入的密码长度不对，或两次密码不一致");

                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected RegisterConstract.Persenter createPersenter() {
        return new RegisterPercenter();
    }

    @Override
    public void RegisterReturn(RegisterBean registerBean) {
        int errno = registerBean.getErrno();
        if (errno == 0) {
            Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (errno == 1000) {
            showError("用户已存在");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO:OnCreate Method has been created, run FindViewById again to generate code
        setContentView(R.layout.activity_register);
        initView();
    }
}
