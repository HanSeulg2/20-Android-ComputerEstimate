package com.example.drua.ui.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drua.API.CPU_Activity;
import com.example.drua.API.GPU_Search_API;
import com.example.drua.API.MAINBOARD_Search_API;
import com.example.drua.API.Parser;
import com.example.drua.API.ParserData;
import com.example.drua.API.Product;
import com.example.drua.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class mainboard extends AppCompatActivity {

    private Button butt_move_mainboard;
    private TextView tv_result_mainboard;
    private TextView tv_price_mainboard;
    public String getPathtext() {
        return pathtext;
    }
    public void setPathtext(String pathtext) {
        this.pathtext = pathtext;
    }
    private String pathtext;
    private Spinner spin_mainboard;
    private Spinner spin_price_mainboard;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainboard);

        tv_result_mainboard=findViewById(R.id.tv_result_mainboard);
        tv_price_mainboard=findViewById(R.id.tv_price_mainboard);
        spin_mainboard=findViewById(R.id.spin_mainboard);
        spin_price_mainboard=findViewById(R.id.spin_price_mainboard);

        spin_mainboard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result_mainboard.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_price_mainboard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_price_mainboard.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        butt_move_mainboard = findViewById(R.id.butt_move_mainboard);
        butt_move_mainboard.setOnClickListener(new View.OnClickListener() {   //글카 검색
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CPU_Activity.class);
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("Product").removeValue();
                ArrayList<String> titlearr = new ArrayList<>();
                ArrayList<String> pricearr = new ArrayList<>();
                ArrayList<String> brandarr = new ArrayList<>();
                ArrayList<String> imagearr = new ArrayList<>();
                ArrayList<String> pdtIdarr = new ArrayList<>();
                ArrayList<Integer> indexArr = new ArrayList<>();

                ParserData psd = new ParserData();
                Parser psr = new Parser();
                Product product = new Product();
                String Out = "Product";
                String In = "product";
                String json = null;
                try {
                    json = new MAINBOARD_Search_API().execute().get();
                    titlearr = psd.TitleParserData(json);
                    pricearr = psd.PriceParser(json);
                    brandarr = psd.BrandParserData(json);
                    imagearr = psd.ImageParserData(json);
                    pdtIdarr = psd.ProductIdParserData(json);


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                String nameText = tv_result_mainboard.getText().toString();
                String priceText = tv_price_mainboard.getText().toString();

                for(int i=0;i<brandarr.size();i++) {
                    String parse = brandarr.get(i);
                    if (parse.equals(nameText)) {
                        switch (priceText) {
                            case "10만원 이하":
                                if (Integer.parseInt(pricearr.get(i)) < 100000) {
                                    indexArr.add(i);
                                    i++;
                                }
                                break;
                            case "10~20만원":
                                if (Integer.parseInt(pricearr.get(i)) >= 100000 && Integer.parseInt(pricearr.get(i)) < 200000) {
                                    indexArr.add(i);
                                    i++;
                                }
                                break;
                            case "20~30만원":
                                if (Integer.parseInt(pricearr.get(i)) >= 200000 && Integer.parseInt(pricearr.get(i)) < 300000) {
                                    indexArr.add(i);
                                    i++;
                                }
                                break;
                            case "30만원 이상":
                                if (Integer.parseInt(pricearr.get(i)) > 300000) {
                                    indexArr.add(i);
                                    i++;
                                }
                                break;
                            default:
                                indexArr.add(i);
                                i++;
                                break;
                        }
                    }else if(nameText.equals("모든 브랜드")){
                        switch (priceText) {
                            case "10만원 이하":
                                if (Integer.parseInt(pricearr.get(i)) < 100000) {
                                    indexArr.add(i);
                                    i++;
                                }
                                break;
                            case "10~20만원":
                                if (Integer.parseInt(pricearr.get(i)) >= 100000 && Integer.parseInt(pricearr.get(i)) < 200000) {
                                    indexArr.add(i);
                                    i++;
                                }
                                break;
                            case "20~30만원":
                                if (Integer.parseInt(pricearr.get(i)) >= 200000 && Integer.parseInt(pricearr.get(i)) < 300000) {
                                    indexArr.add(i);
                                    i++;
                                }
                                break;
                            case "30만원 이상":
                                if (Integer.parseInt(pricearr.get(i)) > 300000) {
                                    indexArr.add(i);
                                    i++;
                                }
                                break;
                            default:
                                indexArr.add(i);
                                i++;
                                break;
                        }
                    }
                }
                for (int i = 0; i < indexArr.size(); i++) {  //index 배열 크기만큼 for
                    String prO = Out + Integer.toString(i);
                    product.setTitle(titlearr.get(indexArr.get(i)));
                    product.setPrice(pricearr.get(indexArr.get(i)));
                    product.setBrand(brandarr.get(indexArr.get(i)));
                    product.setImage(imagearr.get(indexArr.get(i)));
                    product.setProductId(pdtIdarr.get(indexArr.get(i)));
                    mDatabase.child("Product").child(prO).setValue(product);
                }
                setPathtext("Product");
                intent.putExtra("path",getPathtext());
                ////////////////////////////////////////

                startActivity(intent);
            }
        });
    }
    public void exit(View v){
        finish();
    }
}