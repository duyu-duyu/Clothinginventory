package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }
    private void findView(){

        Button ruku = (Button) findViewById(R.id.ruku);
        ruku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, jinhuoActivity.class);
                startActivity(i);
            }
        });
        Button xiaoshou = (Button) findViewById(R.id.xiaoshou);
        xiaoshou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this,guanliActivity.class);
                startActivity(j);
            }
        });
        Button kucun = (Button) findViewById(R.id.pandian);
        kucun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(MainActivity.this,kucunActivity.class);
                startActivity(k);
            }
        });
        Button vip = (Button) findViewById(R.id.VIP);
        vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(MainActivity.this,vipActivity.class);
                startActivity(l);
            }
        });
    }

}