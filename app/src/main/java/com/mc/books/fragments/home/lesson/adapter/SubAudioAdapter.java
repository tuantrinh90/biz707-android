package com.mc.books.fragments.home.lesson.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bon.customview.textview.ExtTextView;
import com.mc.books.R;
import com.mc.common.adapters.BaseRecycleAdapter;
import com.mc.models.home.Role;
import com.mc.models.home.SubTitles;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import java8.util.function.Consumer;

public class SubAudioAdapter extends BaseRecycleAdapter<SubTitles, RecyclerView.ViewHolder> {

    private static final int TYPE_LEFT = 0;
    private static final int TYPE_RIGHT = 1;

    private Context context;
    private Consumer<SubTitles> subTitlesConsumer;
    private Consumer<Object> itemConsumer;
    private List<Role> roles;

    public SubAudioAdapter(Context context, List<Role> roles, Consumer<SubTitles> subTitlesConsumer, Consumer<Object> itemConsumer) {
        this.context = context;
        this.subTitlesConsumer = subTitlesConsumer;
        this.itemConsumer = itemConsumer;
        this.roles = roles;
    }

    @Override
    public int getItemViewType(int position) {
        if (roles != null && roles.size() <= 1)
            return TYPE_LEFT;
        else
            return position % 2 == 0 ? TYPE_LEFT : TYPE_RIGHT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LEFT) {
            View view = LayoutInflater.from(context).inflate(R.layout.subtitle_audio_left, parent, false);
            return new LeftViewHolder(view);
        } else if (viewType == TYPE_RIGHT) {
            View view = LayoutInflater.from(context).inflate(R.layout.subtitle_audio_right, parent, false);
            return new RightViewHolder(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        SubTitles subTitles = listItems.get(position);
        if (holder instanceof LeftViewHolder) {
            switch (subTitles.getColor()) {
                case 0:
                    ((LeftViewHolder) holder).ivChatLeftArrow.setBackground(context.getDrawable(R.drawable.chat_left_arrow_sub_1));
                    ((LeftViewHolder) holder).llChatLeftLayout.setBackground(context.getDrawable(R.drawable.chat_left_layout_sub_1));
                    ((LeftViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_1);
                    break;
                case 1:
                    ((LeftViewHolder) holder).ivChatLeftArrow.setBackground(context.getDrawable(R.drawable.chat_left_arrow_sub_2));
                    ((LeftViewHolder) holder).llChatLeftLayout.setBackground(context.getDrawable(R.drawable.chat_left_layout_sub_2));
                    ((LeftViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_2);
                    break;
                case 2:
                    ((LeftViewHolder) holder).ivChatLeftArrow.setBackground(context.getDrawable(R.drawable.chat_left_arrow_sub_3));
                    ((LeftViewHolder) holder).llChatLeftLayout.setBackground(context.getDrawable(R.drawable.chat_left_layout_sub_3));
                    ((LeftViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_3);
                    break;
                case 3:
                    ((LeftViewHolder) holder).ivChatLeftArrow.setBackground(context.getDrawable(R.drawable.chat_left_arrow_sub_4));
                    ((LeftViewHolder) holder).llChatLeftLayout.setBackground(context.getDrawable(R.drawable.chat_left_layout_sub_4));
                    ((LeftViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_4);
                    break;
                case 4:
                    ((LeftViewHolder) holder).ivChatLeftArrow.setBackground(context.getDrawable(R.drawable.chat_left_arrow_sub_5));
                    ((LeftViewHolder) holder).llChatLeftLayout.setBackground(context.getDrawable(R.drawable.chat_left_layout_sub_5));
                    ((LeftViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_5);
                    break;
                case 5:
                    ((LeftViewHolder) holder).ivChatLeftArrow.setBackground(context.getDrawable(R.drawable.chat_left_arrow_sub_6));
                    ((LeftViewHolder) holder).llChatLeftLayout.setBackground(context.getDrawable(R.drawable.chat_left_layout_sub_6));
                    ((LeftViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_6);
                    break;
                case 6:
                    ((LeftViewHolder) holder).ivChatLeftArrow.setBackground(context.getDrawable(R.drawable.chat_left_arrow_sub_7));
                    ((LeftViewHolder) holder).llChatLeftLayout.setBackground(context.getDrawable(R.drawable.chat_left_layout_sub_7));
                    ((LeftViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_7);
                    break;
                case 7:
                    ((LeftViewHolder) holder).ivChatLeftArrow.setBackground(context.getDrawable(R.drawable.chat_left_arrow_sub_no_role));
                    ((LeftViewHolder) holder).llChatLeftLayout.setBackground(context.getDrawable(R.drawable.chat_left_layout_sub_no_role));
                    ((LeftViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_no_role);
                    break;

            }
            ((LeftViewHolder) holder).tvChatContent.setText(subTitles.getItemSub());
            ((LeftViewHolder) holder).tvChatContent.setOnClickListener(v -> subTitlesConsumer.accept(subTitles));
            ((LeftViewHolder) holder).itemView.setOnClickListener(v -> itemConsumer.accept(null));
        } else if (holder instanceof RightViewHolder) {
            switch (subTitles.getColor()) {
                case 0:
                    ((RightViewHolder) holder).ivChatRightArrow.setBackground(context.getDrawable(R.drawable.chat_right_arrow_sub_1));
                    ((RightViewHolder) holder).llChatRightLayout.setBackground(context.getDrawable(R.drawable.chat_right_layout_sub_1));
                    ((RightViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_1);
                    break;
                case 1:
                    ((RightViewHolder) holder).ivChatRightArrow.setBackground(context.getDrawable(R.drawable.chat_right_arrow_sub_2));
                    ((RightViewHolder) holder).llChatRightLayout.setBackground(context.getDrawable(R.drawable.chat_right_layout_sub_2));
                    ((RightViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_2);
                    break;
                case 2:
                    ((RightViewHolder) holder).ivChatRightArrow.setBackground(context.getDrawable(R.drawable.chat_right_arrow_sub_3));
                    ((RightViewHolder) holder).llChatRightLayout.setBackground(context.getDrawable(R.drawable.chat_right_layout_sub_3));
                    ((RightViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_3);
                    break;
                case 3:
                    ((RightViewHolder) holder).ivChatRightArrow.setBackground(context.getDrawable(R.drawable.chat_right_arrow_sub_4));
                    ((RightViewHolder) holder).llChatRightLayout.setBackground(context.getDrawable(R.drawable.chat_right_layout_sub_4));
                    ((RightViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_4);
                    break;
                case 4:
                    ((RightViewHolder) holder).ivChatRightArrow.setBackground(context.getDrawable(R.drawable.chat_right_arrow_sub_5));
                    ((RightViewHolder) holder).llChatRightLayout.setBackground(context.getDrawable(R.drawable.chat_right_layout_sub_5));
                    ((RightViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_5);
                    break;
                case 5:
                    ((RightViewHolder) holder).ivChatRightArrow.setBackground(context.getDrawable(R.drawable.chat_right_arrow_sub_6));
                    ((RightViewHolder) holder).llChatRightLayout.setBackground(context.getDrawable(R.drawable.chat_right_layout_sub_6));
                    ((RightViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_6);
                    break;
                case 6:
                    ((RightViewHolder) holder).ivChatRightArrow.setBackground(context.getDrawable(R.drawable.chat_right_arrow_sub_7));
                    ((RightViewHolder) holder).llChatRightLayout.setBackground(context.getDrawable(R.drawable.chat_right_layout_sub_7));
                    ((RightViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_7);
                    break;
                case 7:
                    ((RightViewHolder) holder).ivChatRightArrow.setBackground(context.getDrawable(R.drawable.chat_right_arrow_sub_no_role));
                    ((RightViewHolder) holder).llChatRightLayout.setBackground(context.getDrawable(R.drawable.chat_right_layout_sub_no_role));
                    ((RightViewHolder) holder).ivAvatar.setImageResource(R.drawable.ic_avatar_sub_no_role);
                    break;

            }
            ((RightViewHolder) holder).tvChatContent.setText(subTitles.getItemSub());
            ((RightViewHolder) holder).tvChatContent.setOnClickListener(v -> subTitlesConsumer.accept(subTitles));
            ((RightViewHolder) holder).itemView.setOnClickListener(v -> itemConsumer.accept(null));
        }
    }


    class LeftViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivAvatar)
        ImageView ivAvatar;
        @BindView(R.id.ivChatLeftArrow)
        ImageView ivChatLeftArrow;
        @BindView(R.id.llChatLeftLayout)
        LinearLayout llChatLeftLayout;
        @BindView(R.id.tvChatContent)
        ExtTextView tvChatContent;


        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class RightViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivAvatar)
        ImageView ivAvatar;
        @BindView(R.id.ivChatRightArrow)
        ImageView ivChatRightArrow;
        @BindView(R.id.llChatRightLayout)
        LinearLayout llChatRightLayout;
        @BindView(R.id.tvChatContent)
        ExtTextView tvChatContent;


        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
