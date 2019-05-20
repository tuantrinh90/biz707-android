package com.mc.books.fragments.webview;

import com.mc.common.presenters.BaseDataPresenter;
import com.mc.di.AppComponent;

public class WebviewPresenter<V extends IWebviewView> extends BaseDataPresenter<V> implements IWebviewPresenter<V> {
    protected WebviewPresenter(AppComponent appComponent) {
        super(appComponent);
    }
}
