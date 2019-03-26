package com.example.oatj9.freechat.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oatj9.freechat.Database.OpenHelper;
import com.example.oatj9.freechat.R;
import com.example.oatj9.freechat.User.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class login extends AppCompatActivity {
    private Button btn1;
    //声明自己写的 DBOpenHelper 对象
    private OpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        openHelper=new OpenHelper(this);

    }

  //实例化控件
    @BindView(R.id.iv_loginactivity_back)
    ImageView Imbanck;
   @BindView(R.id.et_loginactivity_username)
    EditText et_login;
    @BindView(R.id.et_loginactivity_password)
    EditText et_password;
    //只有两个点击事件
    @OnClick({
             R.id.iv_loginactivity_back,  //导航栏后退点击
             R.id.bt_loginactivity_login,  //登录按钮点击

    })
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_loginactivity_back: //返回到主界面
                Intent m =new Intent(this,MainActivity.class);
                startActivity(m);
                finish();
                break;
            case R.id.bt_loginactivity_login:
                String name = et_login.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = openHelper.getAllData();
                    boolean match = false;
                    for(int i=0;i<data.size();i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())){
                            match = true;
                            break;
                        }else{
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this,mInterface.class);
                        startActivity(intent);
                        finish();//销毁此Activity
                    }else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
