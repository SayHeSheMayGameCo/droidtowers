/*
 * Copyright (c) 2012. HappyDroids LLC, All rights reserved.
 */

package com.happydroids.droidtowers.platform;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.happydroids.droidtowers.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class TowerWebBrowser extends Dialog {
  static final FrameLayout.LayoutParams FILL =
          new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                              ViewGroup.LayoutParams.FILL_PARENT);

  private WebView webView;
  private Activity activity;
  private ProgressDialog progressDialog;
  private FrameLayout container;
  private final String uriToLoad;

  public TowerWebBrowser(Context context, String uriToLoad) {
    super(context, android.R.style.Theme_Translucent_NoTitleBar);
    this.uriToLoad = uriToLoad;
    activity = (Activity) context;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    progressDialog = new ProgressDialog(getContext());
    progressDialog.requestWindowFeature(FEATURE_NO_TITLE);
    progressDialog.setCancelable(false);
    progressDialog.setMessage("Loading...");

    requestWindowFeature(FEATURE_NO_TITLE);

    buildWebView();

    container = new FrameLayout(getContext());

    ImageView closeImage = new ImageView(getContext());
    closeImage.setImageResource(R.drawable.close);
    closeImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        TowerWebBrowser.this.dismiss();
      }
    });

    LinearLayout webViewContainer = new LinearLayout(getContext());
    webViewContainer.addView(webView);
    webViewContainer.setPadding(20, 20, 20, 20);

    container.addView(webViewContainer);
    container.addView(closeImage, new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

    addContentView(container, FILL);
  }

  private void buildWebView() {
    webView = new WebView(getContext());
    webView.setLayoutParams(FILL);
    webView.setHorizontalScrollBarEnabled(false);
    webView.setVerticalScrollBarEnabled(false);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setSavePassword(false);
    webView.getSettings().setSaveFormData(false);

    webView.setWebChromeClient(new WebChromeClient() {
      @Override
      public void onProgressChanged(WebView view, int newProgress) {
        activity.setProgress(newProgress);
      }
    });

    webView.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
      }

      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        progressDialog.show();
        progressDialog.getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressDialog.dismiss();

        setTitle(view.getTitle());
        container.setBackgroundColor(Color.TRANSPARENT);
      }
    });

    if (uriToLoad != null) {
      webView.loadUrl(uriToLoad);
    }
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    // Check if the key event was the Back button and if there's history
    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
      webView.goBack();
      return true;
    }
    // If it wasn't the Back key or there's no web page history, bubble up to the default
    // system behavior (probably exit the activity)
    return super.onKeyDown(keyCode, event);
  }
}
