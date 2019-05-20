package com.mc.books.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bon.customview.button.ExtButton;
import com.bon.customview.textview.ExtTextView;
import com.mc.books.R;
import com.mc.utilities.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java8.util.function.Consumer;

public class AutoNextDialog extends Dialog {
    @BindView(R.id.tvSecond)
    ExtTextView tvSecond;
    @BindView(R.id.btnClose)
    ExtButton btnClose;
    Consumer<Object> objectConsumer;
    CountDownTimer countDownTimer;

    public AutoNextDialog(@NonNull Context context, Consumer<Object> objectConsumer) {
        super(context);
        this.objectConsumer = objectConsumer;
    }

    void setCountDown() {
        countDownTimer = new CountDownTimer(6000, 1000) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onTick(long millisUntilFinished) {
                tvSecond.setText(AppUtils.getTimeCountDownSecond(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                dismiss();
                countDownTimer.cancel();
                objectConsumer.accept(null);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.auto_next_dialog);
            ButterKnife.bind(this);
            setCountDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btnClose)
    public void onClose() {
        dismiss();
    }

    @Override
    public void dismiss() {
        countDownTimer.cancel();
        super.dismiss();
    }
}
