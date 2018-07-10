package com.example.administrator.myapplication.javabean;

import android.webkit.JavascriptInterface;

/**
 * Created by Administrator on 2018/7/10.
 */

public class JsTestInterface {
    private JsBridge jsBridge;

    public JsTestInterface(JsBridge jsBridge) {
        this.jsBridge = jsBridge;
    }

    @JavascriptInterface
    public void  setValue(String value){
        jsBridge.setValueString(value);
    }
}
