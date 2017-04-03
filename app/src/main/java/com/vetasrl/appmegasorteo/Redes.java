package com.vetasrl.appmegasorteo;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Redes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes);

        ImageButton facebookBtn = (ImageButton)findViewById(R.id.facebookBtn);
        ImageButton twitterBtn = (ImageButton)findViewById(R.id.twitterBtn);
        ImageButton instagramBtn = (ImageButton)findViewById(R.id.instagramBtn);
        ImageButton youtubeBtn = (ImageButton)findViewById(R.id.youtubeBtn);
        ImageButton webBtn = (ImageButton)findViewById(R.id.webBtn);

        webBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openWeb(getApplicationContext());
            }
        });

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openFacebook(getApplicationContext());
            }
        });

        twitterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openTwitter(getApplicationContext());
            }
        });

        instagramBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openInstagram();
            }
        });

        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openYoutube();
            }
        });


    }


    public void openWeb(Context context){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bingomegasorteo.com.ar/")));
    }

    public void openFacebook(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/336362966401280")));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/megasorteo")));
        }
    }

    public void openTwitter(Context context){
        try {
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=1470810284")));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/bingomegasorteo")));
        }
    }

    public void openInstagram(){
        Uri uri = Uri.parse("http://instagram.com/_u/bingomegasorteo");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/bingomegasorteo")));
        }
    }

    public void openYoutube(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/user/BingoMegaSorteo"));
        startActivity(intent);
    }


}
