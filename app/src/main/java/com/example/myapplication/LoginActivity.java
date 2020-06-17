package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();

    }

    //跳转界面
    private void findView() {
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginActivity.this, zhuceActivity.class);
                try {
                    startActivity(register);
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, "应用不存在，请下载后再试", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button login = (Button) findViewById(R.id.login);  //登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                //登录事件
                if (TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(password.getText().toString())) {    //是否输入框为空
                    AlertDialog alert = new AlertDialog.Builder(LoginActivity.this).create(); //注册alert事件
                    alert.setTitle("系统提示");      //alert弹窗
                    alert.setMessage("用户名或密码不能为空");
                    alert.show();  //显示

                } else {
                    if (password.getText().toString().equals("123456") && name.getText().toString().equals("admin")) {   //比较是否对应
                        Intent i = new Intent(LoginActivity.this, MainActivity.class); //跳转
                        //启动
                        startActivity(i);
                    } else {
                        AlertDialog alert2 = new AlertDialog.Builder(LoginActivity.this).create();
                        alert2.setTitle("系统提示");
                        alert2.setMessage("用户名或密码出错！");
                        alert2.show();
                    }
                }
            }
        });
    }
}


        /*public void OnMyLoginClick(View v){

      DBOpenHelper helper = new DBOpenHelper(this,"qianbao.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        //根据画面上输入的账号/密码去数据库中进行查询（user_tb是表名）
        Cursor c = db.query("user_tb",null,"userID=? and pwd=?",new String[]{1,1},null,null,null);
        //如果有查询到数据
        if(c!=null && c.getCount() >= 1){
            //可以把查询出来的值打印出来在后台显示/查看
       String[] cols = c.getColumnNames();
        while(c.moveToNext()){
            for(String ColumnName:cols){
                Log.i("info",ColumnName+":"+c.getString(c.getColumnIndex(ColumnName)));
            }
        }
          c.close();
            db.close();

            this.finish();
        }
        //如果没有查询到数据
        else{
            Toast.makeText(this, "手机号或密码输入错误！", Toast.LENGTH_SHORT).show();
        }
       }
    }
}*/
