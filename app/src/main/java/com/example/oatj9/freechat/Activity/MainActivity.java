package com.example.oatj9.freechat.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oatj9.freechat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private Button log,res;
    private TextView textck1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    Button reg;
    @OnClick({
        R.id.login,
        R.id.register
    })
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login: //跳转到登录界面
                Intent intent=new Intent(this,login.class);
                startActivity(intent);
                finish();
                break;
            case  R.id.register: //跳转到注册界面
                Intent m=new Intent(this,register.class);
                startActivity(m);
                finish();
                break;
        }

    }

}
