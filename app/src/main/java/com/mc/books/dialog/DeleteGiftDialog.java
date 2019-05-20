package com.mc.books.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

import com.bon.customview.button.ExtButton;
import com.bon.customview.textview.ExtTextView;
import com.mc.books.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java8.util.function.Consumer;

public class DeleteGiftDialog extends Dialog {

    @BindView(R.id.btnCancel)
    ExtButton btnCancel;
    @BindView(R.id.btnDelete)
    ExtButton btnDelete;
    @BindView(R.id.tvError)
    ExtTextView tvError;
    private Consumer<String> deleteConsumer;
    String giftName;

    public DeleteGiftDialog(@NonNull Context context, String giftName, Consumer<String> deleteConsumer) {
        super(context);
        this.deleteConsumer = deleteConsumer;
        this.giftName = giftName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_delete_gift);
        ButterKnife.bind(this);
        tvError.setText("Bạn có chắc chắn muốn xoá quà tặng \"" + giftName + "\"?");
    }

    @OnClick({R.id.btnCancel, R.id.btnDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDelete:
                dismiss();
                deleteConsumer.accept("");
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
