package com.vetasrl.appmegasorteo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class EnVivo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_vivo);

//        WebView radio1 = (WebView)findViewById(R.id.radio1);
//        WebView radio2 = (WebView)findViewById(R.id.radio2);
        WebView radio3 = (WebView)findViewById(R.id.radio3);
//        WebView radio4 = (WebView)findViewById(R.id.radio4);
//        WebView radio5 = (WebView)findViewById(R.id.radio5);
        WebView radio6 = (WebView)findViewById(R.id.radio6);
        WebView radio7 = (WebView)findViewById(R.id.radio7);
//        WebView radio8 = (WebView)findViewById(R.id.radio8);
//        WebView radio9 = (WebView)findViewById(R.id.radio9);
        Button facebook = (Button)findViewById(R.id.facebook);

//        radio1.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><audio controls='' autoplay='' name='1'><source src='http://radio2.radio.rosario3.com:8000/prueba2' type='audio/mpeg'></audio></td></tr></table></body></html>", "html/css", "utf-8", null);
//        radio2.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><audio controls='' autoplay='' name='2'><source src='http://200.58.118.108:8172/stream' type='audio/aac'></audio></td></tr></table></body></html>", "html/css", "utf-8", null);

        radio3.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><audio controls='' autoplay='' name='3'><source src='http://108.163.190.212:9500/;' type='audio/mpeg'></audio></td></tr></table></body></html>", "html/css", "utf-8", null);

//        radio4.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><audio controls='' autoplay='' name='4'><source src='http://200.58.106.247:8524/;stream/1' type='audio/mpeg'></audio></td></tr></table></body></html>", "html/css", "utf-8", null);
//        radio5.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><audio controls='' autoplay='' name='5'><source src='http://hostingystreaming.com:4018/;' type='audio/mpeg'></audio></td></tr></table></body></html>", "html/css", "utf-8", null);
        radio6.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><audio controls='' autoplay='' name='6'><source src='http://server1.veemesoft.com.ar:9760/;' type='audio/mpeg'></audio></td></tr></table></body></html>", "html/css", "utf-8", null);

        radio7.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><audio controls='' autoplay='' name='7'><source src='http://media203.services.digitar.net:8035/lasparejas.mp3' type='audio/mpeg'></audio></td></tr></table></body></html>", "html/css", "utf-8", null);

//        radio8.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><audio controls='' autoplay='' name='8'><source src='http://radio2.radio.rosario3.com:8000/prueba2' type='audio/mpeg'></audio></td></tr></table></body></html>", "html/css", "utf-8", null);
//        radio9.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><audio controls='' autoplay='' name='9'><source src='http://streamcasthd.com:25466/live' type='audio/aac'></audio></td></tr></table></body></html>", "html/css", "utf-8", null);

        facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(newFacebookIntent(getApplicationContext().getPackageManager(), "https://www.facebook.com/megasorteo"));
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
