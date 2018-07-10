package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myapplication.javabean.JsBridge;
import com.example.administrator.myapplication.javabean.JsTestInterface;

public class MainActivity extends AppCompatActivity implements JsBridge{

    private WebView mWebView;
    private TextView mTextView;
    private Handler mHandler;
    private EditText mEditText;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
        initData();
    }

    private void initData() {
        /**
         * web调用Java方法
         */
        //1、允许WebView加载js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //2、编写js接口类
        //3、给webview添加js接口
        mWebView.addJavascriptInterface(new JsTestInterface(this),"testJsInterface");
        //4、加载js文件
        String path =" file:///android_asset/index.html";
        mWebView.loadUrl(path);
        /**
         * js调用Android方法
         */
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString().trim();
                mWebView.loadUrl("javascript:if(window.remote){window.remote('"+text+"')}");
            }
        });
    }

    private void initView(Bundle savedInstanceState) {
        mWebView = findViewById(R.id.wv_test);
        mTextView = findViewById(R.id.tv_test);
        mHandler = new Handler();
        mEditText = findViewById(R.id.et_test);
        mButton = findViewById(R.id.bt_test);
    }

    @Override
    public void setValueString(final String value) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(value);
            }
        });
    }
}
