package com.example.ceren.tid2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    private TextView text1;
    Context context = this;
    MyService myservice;
    BottomNavigationItemView home, myAccount, search;
    CardView cardView1;
    CardView cardView2;
    CardView cardView3;
    CardView cardView4;
    CardView cardView5;
    CardView cardView6;
    CardView cardView7;
    CardView cardView8;
    private static int dersID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text1 = (TextView) findViewById(R.id.text1);
        cardView1 = (CardView) findViewById(R.id.cardView1);
        cardView2 = (CardView) findViewById(R.id.cardView2);
        cardView3 = (CardView) findViewById(R.id.cardView3);
        BottomNavigationItemView home = (BottomNavigationItemView) findViewById(R.id.home);
        BottomNavigationItemView myAccount = (BottomNavigationItemView) findViewById(R.id.myAccount);
        BottomNavigationItemView search = (BottomNavigationItemView) findViewById(R.id.search);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);

                    dersID = 1;

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//                intent.putExtra("MyKey",dersID);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);

                    dersID = 2;

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              //  intent.putExtra("MyKey",dersID);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);

                    dersID = 3;

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //  intent.putExtra("MyKey",dersID);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main6Activity.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, search.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

   public static int getDersID(){
       return dersID;
   }


    }