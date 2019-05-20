package com.mc.books.fragments.webview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.mc.books.R;
import com.mc.common.fragments.BaseMvpFragment;

import butterknife.BindView;

public class WebviewFragment extends BaseMvpFragment<IWebviewView, IWebviewPresenter<IWebviewView>> implements IWebviewView {

    @BindView(R.id.webview)
    WebView webView;

    public static WebviewFragment newInstance() {
        Bundle args = new Bundle();
        WebviewFragment fragment = new WebviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IWebviewPresenter<IWebviewView> createPresenter() {
        return new WebviewPresenter<>(getAppComponent());
    }

    @Override
    public void initToolbar(@NonNull ActionBar supportActionBar) {
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_right);
        supportActionBar.show();
    }

    @Override
    public int getTitleId() {
        return R.string.app_name;
    }

    @Override
    public int getResourceId() {
        return R.layout.webview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindButterKnife(view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("https://beta.app.mcbooks.vn/chi-tiet-sach-embed/bai-tap/421-sach-khong-xoa/cau-hoi-17/4532/646218ca-f4a3-4a42-addd-c9e703d21c84");
    }
}
