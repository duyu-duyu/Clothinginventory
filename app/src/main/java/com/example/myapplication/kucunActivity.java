package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class kucunActivity extends MainActivity implements View.OnClickListener{
    MyHelper myHelper;
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtEmail;
    private TextView mTvShow;
    private Button mBtAdd;
    private Button mBtQuery;
    private Button mBtUpdate;
    private Button mBtDelete;
    public String name;
    public String phone;
    public String email;
    public SQLiteDatabase db;
    public ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kucun);
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
        mBtUpdate = (Button) findViewById(R.id.btn_update);
        mBtQuery = (Button) findViewById(R.id.btn_query);
        mBtDelete = (Button) findViewById(R.id.btn_delete);


        mBtQuery.setOnClickListener(this);

    }

    @Override
    //点击事件
    public void onClick(View v) {
        switch(v.getId()) {
            //添加数据
            case R.id.btn_add:
                addText();
                queryText();
                break;
            //查询数据
            case R.id.btn_query:
                queryText();
                break;
            //修改数据
            case R.id.btn_update:
                updateText();
                queryText();
                break;
            //删除数据
            case R.id.btn_delete:
                deleteText();
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
        Toast.makeText(this,"添加数据成功",Toast.LENGTH_SHORT).show();
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
            mTvShow.setText("goodsid:" + cursor.getString(1) + "\t" + "goodsname:" + cursor.getString(2) + "\t" + "goodsprice:" + cursor.getString(3));
        }
        while(cursor.moveToNext()) {
            mTvShow.append("\n" + "goodsid:" + cursor.getString(1) + "\t" + "|goodsname:" + cursor.getString(2) + "\t" + "|goodsprice:" + cursor.getString(3));
        }
        cursor.close();
        db.close();
    }
    //修改数据
    public void updateText() {
        db = myHelper.getWritableDatabase();
        //实例化一个要修改的数据的对象
        values = new ContentValues();
        values.put("phone",phone = mEtPhone.getText().toString());
        values.put("email",email = mEtEmail.getText().toString());
        //更新并得到行数
        db.update("information",values,"name = ?",new String[]{mEtName.getText().toString()});
        Toast.makeText(this, "数据修改成功", Toast.LENGTH_SHORT).show();
        db.close();
    }
    //删除数据
    public void deleteText() {
        db = myHelper.getWritableDatabase();
        int i = db.delete("information","name=?",new String[]{mEtName.getText().toString()});
        if(i !=0 ) {
            Toast.makeText(this,"删除数据成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"删除数据失败",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context) {
            super(context,"lvdou.db",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table information(_id integer primary key autoincrement, name varchar(20), phone varchar(20), email varchar(20))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
