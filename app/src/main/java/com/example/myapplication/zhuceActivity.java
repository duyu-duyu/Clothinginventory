package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class zhuceActivity extends LoginActivity implements View.OnClickListener{
    MyHelper myHelper;
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtEmail;
    private TextView mTvShow;
    private Button mBtAdd;
    public String name;
    public String phone;
    public String email;
    public SQLiteDatabase db; //命名为db
    public ContentValues values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        myHelper = new MyHelper(this);
        init();
    }
    //实例化界面，获取各个控件的值
    private void init() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mTvShow = (TextView) findViewById(R.id.ld_show);
        mBtAdd = (Button) findViewById(R.id.btn_add);
        mBtAdd.setOnClickListener(this);
    }
    @Override
    //点击事件
    public void onClick(View v) {
        switch(v.getId()) {
            //添加数据
            case R.id.btn_add:
                addText();
                //queryText();
                break;
            //查询数据
            case R.id.btn_query:
                queryText();
                break;
        }
    }
    //添加数据
    public void addText() {
        name = mEtName.getText().toString();
        phone = mEtPhone.getText().toString();
        email = mEtEmail.getText().toString();
        //获取可读写的SQLiteDatabase对象
        db = myHelper.getWritableDatabase();
        //创建ContentValues对象
        values = new ContentValues();
        //将数据添加到ContentValues对象
        values.put("name",name);
        values.put("phone",phone);
        values.put("email",email);
        db.insert("information",null,values);
        //底部弹窗
        Toast.makeText(this,"添加数据成功",Toast.LENGTH_SHORT).show();
        //关闭数据库连接
        db.close();
    }
    //查询数据
    public void queryText() {
        db = myHelper.getWritableDatabase();
        Cursor cursor = db.query("information",null,null,null,null,null,null);
        if(cursor.getCount() == 0) {
            mTvShow.setText("");
            Toast.makeText(this,"没有数据",Toast.LENGTH_SHORT).show();
        }
        else{
            cursor.moveToFirst();
            mTvShow.setText("用户名:" + cursor.getString(1) + "\t" + "密码:" + cursor.getString(2) + "\t" + "email:" + cursor.getString(3));
        }
        while(cursor.moveToNext()) {
            mTvShow.append("\n" + "用户名:" + cursor.getString(1) + "\t" + "|密码:" + cursor.getString(2) + "\t" + "|email:" + cursor.getString(3));
        }
        cursor.close();
        db.close();
    }
    //修改数据

    }
    class MyHelper extends SQLiteOpenHelper {
    //创建数据库
        public MyHelper(Context context) {
            super(context,"lvdou.db",null,1);
        }
//创建表
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table information(_id integer primary key autoincrement, name varchar(20), phone varchar(20), email varchar(20))");
        }
//更新
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

