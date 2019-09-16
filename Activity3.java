package com.example.ceren.tid2;
import android.content.Intent;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {
    ImageView imageView1;
    WebView wb;
    TextView textView1;
    Button buton_next , buton, buton_prev;
    SeekBar packageRange;
    private int currentVideo=0;
    private int currentText=0;
    BottomNavigationItemView home,myAccount,search ;
   int dersID = MainActivity.getDersID();
    ArrayList<String[]> split = new ArrayList<String[]>();
    ArrayList<String> array_gif1 = new ArrayList<String>();
    ArrayList<String> array_name1 = new ArrayList<String>();



    String gif1[]={"https://thumbs.gfycat.com/ImpracticalDamagedBordercollie-size_restricted.gif",
                   "https://thumbs.gfycat.com/DemandingAbleGalapagospenguin-size_restricted.gif",
                   "https://thumbs.gfycat.com/LinedGiftedKoodoo-size_restricted.gif",
                   "https://thumbs.gfycat.com/SeparateParchedAlaskanhusky-size_restricted.gif"};

    String text1[]={"aptal kedi", "sinirli kedi", "muzikli kedi", "bulut"};

    String gif2[]={"https://thumbs.gfycat.com/EquatorialDecisiveGnat-size_restricted.gif",
            "https://thumbs.gfycat.com/BouncyUnfoldedBobwhite-size_restricted.gif",
            "https://thumbs.gfycat.com/SingleWateryDuck-size_restricted.gif",
            "https://thumbs.gfycat.com/TiredVelvetyArcticduck-size_restricted.gif",
            "https://thumbs.gfycat.com/MildForkedAlligatorgar-size_restricted.gif"};

    String text2[]={"avakado", "kalpli ayi", "yuvarlak", "sarilan ayilar", "dunya"};

    String gif3[]={"https://thumbs.gfycat.com/DarlingImpishAmericancreamdraft-size_restricted.gif",
            "https://thumbs.gfycat.com/ElaborateMarriedLeafwing-size_restricted.gif"};

    String text3[]={"yaprak", "sevmek"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);


        home = (BottomNavigationItemView) findViewById(R.id.home);
        myAccount = (BottomNavigationItemView) findViewById(R.id.myAccount);
        search = (BottomNavigationItemView) findViewById(R.id.search);

        wb = (WebView) findViewById(R.id.wb);
        buton_next = (Button) findViewById(R.id.buton_next);
        buton_prev = (Button) findViewById(R.id.buton_prev);
        buton = (Button) findViewById(R.id.buton);
        packageRange = (SeekBar) findViewById(R.id.packageRange);
        textView1 = (TextView) findViewById(R.id.textView1);
        imageView1 = (ImageView) findViewById(R.id.imageView1);

        try {
            URL url = new URL("http://192.168.0.105/getGif.php?ders=" + dersID);
            URLConnection uc = url.openConnection();
            uc.setConnectTimeout(10000);
            uc.setReadTimeout(10000);
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = in.readLine();
            uc.connect();

            if (line  == null) {
                Toast.makeText(Activity3.this,"Bos geliyor", Toast.LENGTH_LONG).show();

               in.close();
            }else{

                String array[] = line.split(",");
                Toast.makeText(Activity3.this,"Baglandi", Toast.LENGTH_LONG).show();

                in.close();
            }
        }
        catch (IOException e){
            Toast.makeText(Activity3.this,"Bağlantı yok", Toast.LENGTH_LONG).show();
        }


    switch (dersID) {
         case 1:
           wb.loadUrl(gif1[(currentVideo)]);
           textView1.setText(text1[(currentText)]);
           break;

         case 2:
             wb.loadUrl(gif2[currentVideo]);
             textView1.setText(text2[currentText]);
             break;

        case 3:
            wb.loadUrl(gif3[currentVideo]);
            textView1.setText(text3[currentText]);
            break;

     }
        buton_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentVideo<4 &&currentText<4) {
                    currentVideo++;
                    currentText++;

                    switch (dersID) {
                        case 1:
                            wb.loadUrl(gif1[currentVideo]);
                            textView1.setText(text1[currentText]);
                            break;

                        case 2:
                            wb.loadUrl(gif2[currentVideo]);
                            textView1.setText(text2[currentText]);
                            break;

                        case 3:
                            wb.loadUrl(gif3[currentVideo]);
                            textView1.setText(text3[currentText]);
                            break;

                    }
                    wb.getSettings().setLoadWithOverviewMode(true);
                    wb.getSettings().setUseWideViewPort(true);

                }
            }
        });

        buton_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentVideo>0 && currentText>0) {
                    currentVideo--;
                    currentText--;

                    switch (dersID) {
                        case 1:
                            wb.loadUrl(gif1[currentVideo]);
                            textView1.setText(text1[currentText]);
                            break;

                        case 2:
                            wb.loadUrl(gif2[currentVideo]);
                            textView1.setText(text2[currentText]);
                            break;

                        case 3:
                            wb.loadUrl(gif3[currentVideo]);
                            textView1.setText(text3[currentText]);
                            break;

                    }

                    wb.getSettings().setLoadWithOverviewMode(true);
                    wb.getSettings().setUseWideViewPort(true);

                    }}
                });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentVideo>-1){
                    currentVideo=currentVideo--;
                    switch (dersID) {
                        case 1:
                            wb.loadUrl(gif1[currentVideo]);
                            textView1.setText(text1[currentText]);
                            break;

                        case 2:
                            wb.loadUrl(gif2[currentVideo]);
                            textView1.setText(text2[currentText]);
                            break;

                        case 3:
                            wb.loadUrl(gif3[currentVideo]);
                            textView1.setText(text3[currentText]);
                            break;

                    }

                    wb.getSettings().setLoadWithOverviewMode(true);
                    wb.getSettings().setUseWideViewPort(true);
                }
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity3.this,MainActivity.class);
                startActivity(intent);
            }
        });

        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity3.this,Main6Activity.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity3.this,search.class);
                startActivity(intent);
            }
        });

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, Activity4.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

}