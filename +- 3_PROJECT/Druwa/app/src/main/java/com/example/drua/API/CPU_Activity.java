package com.example.drua.API;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drua.MainActivity;
import com.example.drua.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CPU_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager; // recyclerview는 필요함!
    private ArrayList<com.example.drua.API.Product> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    public CPU_Activity(){}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent cpuintent = getIntent();
        //Path path = new Path();
        MainActivity main = new MainActivity();
        recyclerView = findViewById(R.id.recycler); //id 연결
        recyclerView.setHasFixedSize(true); //recyclerview 성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User 객체를 담음
        database = FirebaseDatabase.getInstance(); //firebase 기능 연동

        String path = cpuintent.getStringExtra("path");

        databaseReference = database.getReference(path); //db 테이블 연동         String.valueOf(path.getPath())
            Log.e("databaseFath", String.valueOf(path));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {  //db 데이터를 받아오는 역할

                arrayList.clear();                   //기존 배열 초기화
                for(DataSnapshot snapshot : datasnapshot.getChildren()){  // 반복문으로 데이터list 추출
                    com.example.drua.API.Product pd = snapshot.getValue(com.example.drua.API.Product.class);//만들어진 Product 클래스에 보내서 데이터 사용가능하게 만듦.
                    arrayList.add(pd);  // arraylist에 데이터 추가완료 recyclerview에 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {     //에러 발생시

            finish();

            }
        });

        adapter = new com.example.drua.API.CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);                //recyclerview에 어댑터 연결

    }



}








