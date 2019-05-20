package com.mc.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bon.customview.textview.ExtTextView;
import com.bon.util.DateTimeUtils;
import com.mc.books.R;
import com.mc.customizes.recyclerview.ExtRecyclerViewAdapter;
import com.mc.customizes.recyclerview.ExtRecyclerViewHolder;
import com.mc.models.notification.NotificationSystem;
import com.mc.utilities.AppUtils;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import java8.util.function.Consumer;

public class NotificationSystemAdapter extends ExtRecyclerViewAdapter<NotificationSystem, NotificationSystemAdapter.NotificationSystemViewHolder> {
    private Context context;
    private Consumer<NotificationSystem> notificationSysConsumer;

    public NotificationSystemAdapter(Context context, List<NotificationSystem> items, Consumer<NotificationSystem> consumer) {
        super(context, items);
        this.context = context;
        this.notificationSysConsumer = consumer;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_notification;
    }

    @Override
    protected NotificationSystemViewHolder onCreateHolder(View view, int viewType) {
        return new NotificationSystemViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotificationSystemViewHolder holder, NotificationSystem notificationSys, int position) {
        try {
            if (notificationSys != null) {
                AppUtils.setImageGlide(context, notificationSys.getMediaUri() != null ?
                        notificationSys.getMediaUri() : "", R.mipmap.ic_launcher_round, holder.imgBookAvatar);
                holder.txtTitleBook.setText(notificationSys.getTitle());
                holder.txtTitleTime.setText(DateTimeUtils.getTimeAgos(notificationSys.getCreatedAt()));
                holder.llContaner.setBackground(context.getDrawable(R.drawable.bg_yellow_gradient));
                holder.llContaner.setOnClickListener(v -> notificationSysConsumer.accept(notificationSys));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("notificationSys", "notificationSys: " + e);
        }
    }


    public static class NotificationSystemViewHolder extends ExtRecyclerViewHolder {
        @BindView(R.id.imgBookAvatar)
        CircleImageView imgBookAvatar;
        @BindView(R.id.txtTitleBook)
        ExtTextView txtTitleBook;
        @BindView(R.id.txtTitleTime)
        ExtTextView txtTitleTime;
        @BindView(R.id.llContaner)
        LinearLayout llContaner;

        public NotificationSystemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
