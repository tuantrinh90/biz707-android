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

public class DeleteBookDialog extends Dialog {

    @BindView(R.id.btnCancel)
    ExtButton btnCancel;
    @BindView(R.id.txtError)
    ExtTextView txtError;
    @BindView(R.id.btnDelete)
    ExtButton btnDelete;
    private Consumer<String> deleteConsumer;
    Context context;
    String bookName;

    public DeleteBookDialog(@NonNull Context context, String bookName, Consumer<String> deleteConsumer) {
        super(context);
        this.deleteConsumer = deleteConsumer;
        this.bookName = bookName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_delete_book);
        ButterKnife.bind(this);
        txtError.setText("Bạn có chắc chắn muốn xoá Sách \"" + bookName + "\"?");
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
