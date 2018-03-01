package com.wdtpr.augus.jsoupdemo.jsoupModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * Created by augus on 2018/2/27.
 */

public class JsoupParser {
    //
    private Gson gson;
    //timeout ms 預設10s
    private int timeout = 10000;
    //
    private String Baidu_title = "window.__INITIAL_STATE__=";
    //
    private ExecutorService mExecutor;//線程管理
    private JsoupCallBack jsoupCallBack;

    public JsoupParser(ExecutorService mExecutor, JsoupCallBack jsoupCallBack) {
        this.gson = new GsonBuilder().create();
        this.mExecutor = mExecutor;
        this.jsoupCallBack = jsoupCallBack;
    }

    /**
     * 設定timeout
     *
     * @param timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 台灣
     */
    public void getVersionTW() {
        mExecutor.execute(tw);
    }

    private Runnable tw = new Runnable() {
        @Override
        public void run() {
            try {
                String Version = Jsoup.connect(JsoupURL.GOOGLE)
                        .timeout(timeout).get()
                        .select("div[itemprop=softwareVersion]")
                        .first().text();
                jsoupCallBack.callback(Version);
            } catch (IOException e) {
                e.printStackTrace();
                jsoupCallBack.error(e.getMessage());
            }
        }
    };

    /**
     * 大陸
     */
    public void getVersionCN() {
        mExecutor.execute(cn);
    }

    private Runnable cn = new Runnable() {
        @Override
        public void run() {
            try {

                Elements result1 = Jsoup.connect(JsoupURL.BAIDU)
                        .timeout(timeout).get().select("script");
                String Version = null;
                //
                boolean isNull = false;
                if (result1 == null) {
                    isNull = true;
                }
                if (result1.size() == 0) {
                    isNull = true;
                }
                if (isNull) {
                    jsoupCallBack.error("NULL PARSER");
                }

                //
                Jsoup_cn_baidu bean;
                for (Element elements : result1) {
                    if (elements.data().contains(Baidu_title)) {
                        //get model Jsoup_cn_baidu
                        LogUtils.d("data: " + elements.data().substring(Baidu_title.length()));
                        bean = gson.fromJson(elements.data().substring(Baidu_title.length()), Jsoup_cn_baidu.class);
                        LogUtils.d(bean.toString());
                        Version = bean.getAppDoc().getResList().getD_23282776().getData().getAppDocNew().getVersion();
                    }
                }

                jsoupCallBack.callback(Version);
            } catch (IOException e) {
                e.printStackTrace();
                jsoupCallBack.error(e.getMessage());
            }
        }
    };

    //結束時呼叫
    public void onDestroy() {
        if (!mExecutor.isShutdown()) mExecutor.shutdown();
        if (jsoupCallBack != null) jsoupCallBack = null;
        if (gson != null) gson = null;
    }
}
