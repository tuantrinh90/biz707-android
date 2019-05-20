package com.bon.customview.textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bon.library.R;
import com.bon.logger.Logger;
import com.bon.util.StringUtils;
import com.bon.util.TypefacesUtils;

/**
 * Created by user on 4/22/2015.
 */
@SuppressLint("AppCompatCustomView")
public class ExtTextView extends TextView {
    private static final String TAG = ExtTextView.class.getSimpleName();

    public ExtTextView(Context context) {
        super(context);
    }

    public ExtTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyAttributes(context, attrs);
    }

    public ExtTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyAttributes(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     */
    private void applyAttributes(Context context, AttributeSet attrs) {
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExtTextView);
            String fontPath = typedArray.getString(R.styleable.ExtTextView_textViewFontAssetName);
            if (StringUtils.isEmpty(fontPath)) fontPath = TypefacesUtils.FONT_DEFAULT;
            this.setTypeface(TypefacesUtils.get(getContext(), fontPath));
            this.setPaintFlags(this.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            typedArray.recycle();
        } catch (Exception e) {
            Logger.e(TAG, e);
        }
    }
}
