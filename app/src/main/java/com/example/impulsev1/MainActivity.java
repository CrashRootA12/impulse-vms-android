package com.example.impulsev1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    Dialog MyDialog;
    ImageButton close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl=findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.about:
                        AboutDialog();
                        break;
                    case R.id.contact:
                        Contact();
                        break;
                    case R.id.feedback:
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"shivam.singhal.sd@mmumullana.org"});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject");
                        emailIntent.setType("message/rfc822");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
                        startActivity(emailIntent);
                        break;
                        default:
                        Toast.makeText(MainActivity.this,"Some Error",Toast.LENGTH_SHORT).show();
                }

                return true;


            }
        });

        mywebView =(WebView)findViewById(R.id.webview);
        WebSettings webSettings = mywebView.getSettings();
        mywebView.loadUrl("https://impulsetrialfirst.herokuapp.com/");
        webSettings.setJavaScriptEnabled(true);



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public class myWebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack()) {
            mywebView.goBack();
        }else{
                super.onBackPressed();
            }
        }

    public void AboutDialog(){
        MyDialog = new Dialog(MainActivity.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.customdialog);
        MyDialog.setTitle("My Custom Dialog");

        close = (ImageButton) MyDialog.findViewById(R.id.close);

        close.setEnabled(true);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });

        MyDialog.show();
    }

    public void Contact(){
        MyDialog = new Dialog(MainActivity.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.contactdialogactivity);
        MyDialog.setTitle("My Custom Dialog");
        TextView title=findViewById(R.id.titleId);
//        TextView content = (TextView) findViewById(R.id.text_contact);
//
//       content.setText("For any query and concern.Contact us:1.821885151 2.9602141215");
        close = (ImageButton) MyDialog.findViewById(R.id.close);

        close.setEnabled(true);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });

        MyDialog.show();
    }


}
