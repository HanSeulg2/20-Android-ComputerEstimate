package com.example.drua;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.drua.API.SelectProduct_Activity;
import com.example.drua.fragment.FragmentBoard;
import com.example.drua.fragment.FragmentCategory;
import com.example.drua.fragment.FragmentHome;
import com.example.drua.fragment.FragmentNotice;
import com.example.drua.fragment.FragmentQna;
import com.example.drua.ui.show.cpu;
import com.example.drua.ui.show.graphic_card;
import com.example.drua.ui.show.hdd;
import com.example.drua.ui.show.mainboard;
import com.example.drua.ui.show.pc_case;
import com.example.drua.ui.show.power;
import com.example.drua.ui.show.ram;
import com.example.drua.ui.show.ssd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentNotice fragmentNotice = new FragmentNotice();
    private FragmentBoard fragmentBoard = new FragmentBoard();
    private FragmentCategory fragmentCategory = new FragmentCategory();
    private FragmentQna fragmentQna = new FragmentQna();
    private String pathtext;


    private Button butt_select_product;
    public String getPathtext() {
        return pathtext;
    }
    public void setPathtext(String pathtext) {
        this.pathtext = pathtext;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Product").removeValue();
        mDatabase.child("Select_Product").removeValue();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }




    public void show_cpu(View v){
        Intent it = new Intent(getApplicationContext(), cpu.class);
        startActivity(it);

    }
    public void show_mainboard(View v){
        Intent it = new Intent(getApplicationContext(), mainboard.class);
        startActivity(it);

    }
    public void show_ram(View v){
        Intent it = new Intent(getApplicationContext(), ram.class);
        startActivity(it);
    }
    public void show_graphic_card(View v){
        Intent it = new Intent(getApplicationContext(), graphic_card.class);
        startActivity(it);
    }
    public void show_power(View v){
        Intent it = new Intent(getApplicationContext(), power.class);
        startActivity(it);
    }
    public void show_pc_case(View v){
        Intent it = new Intent(getApplicationContext(), pc_case.class);
        startActivity(it);
    }
    public void show_hdd(View v){
        Intent it = new Intent(getApplicationContext(), hdd.class);
        startActivity(it);
    }
    public void show_ssd(View v){
        Intent it = new Intent(getApplicationContext(), ssd.class);
        startActivity(it);
    }

    public void select_product(View v){
        Toast.makeText(this, "담은물품 화면", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, SelectProduct_Activity.class);
        setPathtext("Select_Product");
        intent.putExtra("path",getPathtext());
        startActivity(intent);
    }






    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.categoryItem:
                    transaction.replace(R.id.frameLayout, fragmentCategory).commitAllowingStateLoss();
                    break;
                case R.id.qnaItem:
                    transaction.replace(R.id.frameLayout, fragmentQna).commitAllowingStateLoss();
                    break;
                case R.id.homeItem:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.boardItem:
                    transaction.replace(R.id.frameLayout, fragmentBoard).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}