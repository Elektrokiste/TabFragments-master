package org.ptyagicodecamp.webview_lib;

import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.WebView;

/**
 * Created by ptyagi on 12/15/16.
 */

public class WebViewHelper {
    private static final String TAG = "webview-helper";

    private WebView mWebView;

    public WebViewHelper(WebView webView) {
        mWebView = webView;
    }

    public void loadUrl(String url) {
        if (URLUtil.isValidUrl(url)) {
            mWebView.loadUrl(url);
        } else {
            Log.e(TAG, "Loaded invalid url in webview");
        }
    }
    public void loadUrl(WebView webView, String url) {
        mWebView = webView;
        if (URLUtil.isValidUrl(url)) {
            webView.loadUrl(url);
        } else {
            Log.e(TAG, "Loaded invalid url in webview");
        }
    }

    /**
     * Utility method to enable Javascript in current WebView
     */
    public void enableJS() {
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    /**
     * Utility method to disable Javascript in current WebView
     */
    public void disableJS() {
        mWebView.getSettings().setJavaScriptEnabled(false);
    }

    /**
     * Utility method to enable Javascript in a given WebView
     * @param webView
     */
    public void enableJS(WebView webView) {
        mWebView = webView;
        webView.getSettings().setJavaScriptEnabled(true);
    }

    /**
     * Utility method to disable Javascript in a given WebView
     * @param webView
     */
    public void disableJS(WebView webView) {
        mWebView = webView;
        webView.getSettings().setJavaScriptEnabled(false);
    }

    /**
     * Returns current active WebView
     * @return
     */
    public WebView getWebView() {
        return mWebView;
    }

    /**
     * Display data in WebView in "text/html" MIIME-type and
     * UTF-8 encoding
     * @param data
     */
    public void loadData(String data) {
        if (mWebView instanceof WebView) {
            mWebView.loadData(data, "text/html", "UTF-8");
        } else {
            Log.e(TAG, "WebView not found");
        }
    }
}
