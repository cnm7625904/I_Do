package com.example.administrator.i_do;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           web=findViewById(R.id.web);
           init();
           findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   web.loadUrl("javascript:alertMessage('JS交互')");
                   String js="h5交互";
                   web.loadUrl("javascript:alertMessage(\""+js+"\")");
                   web.evaluateJavascript("sum(2,3)", new ValueCallback<String>() {
                       @Override
                       public void onReceiveValue(String s) {
                           Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                       }
                   });
               }
           });
    }

    private void init() {
    web.loadUrl("file:///android_asset/test.html");
        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        web.addJavascriptInterface(new JsInteration(),"android");
//        web.addJavascriptInterface(new AllJava(),"android");
    }
    //给h5调用的
    public class JsInteration{
        @JavascriptInterface
        public String back(){
            return "消息来源于JAVA";
        }
    }
}
