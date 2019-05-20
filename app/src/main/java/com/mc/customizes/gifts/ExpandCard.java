package com.mc.customizes.gifts;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bon.customview.textview.ExtTextView;
import com.bon.interfaces.Optional;
import com.mc.books.R;
import com.mc.common.adapters.BaseRecycleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ExpandCard extends LinearLayout {

    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.viewContainer)
    LinearLayout viewContainer;
    @BindView(R.id.header)
    LinearLayout header;
    @BindView(R.id.title)
    ExtTextView title;
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.rvContent)
    RecyclerView rvContent;
    Unbinder unbinder;
    Context context;
    boolean isShow = false;

    public ExpandCard(Context context) {
        super(context);
        initView(context, null);
    }

    public ExpandCard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ExpandCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ExpandCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.expand_card, this);
        unbinder = ButterKnife.bind(this, view);

    }

    public void setAdapter(BaseRecycleAdapter adapter) {
        rvContent.setAdapter(adapter);
        rvContent.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setTitle(String value) {
        title.setText(value);

    }

    @OnClick(R.id.header)
    void clickExpand() {
        if (isShow) {
            isShow = false;
            llContent.setVisibility(View.GONE);
            arrow.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_down));
        } else {
            isShow = true;
            llContent.setVisibility(View.VISIBLE);
            arrow.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_up));
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

}
