package com.aid.trade.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.aid.trade.R;

/**
 * The text image view
 */
public class TextImageView extends AppCompatImageView {
    /**
     * The text.
     */
    private String text;
    /**
     * The text size.
     */
    private float textSize;
    /**
     * The text paint.
     */
    private TextPaint textPaint;
    /**
     * The text bounds.
     */
    private Rect bounds;

    public TextImageView(Context context) {
        super(context);
    }

    public TextImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextImageView);
        text = a.getString(R.styleable.TextImageView_tiv_text);
        textSize = a.getDimension(R.styleable.TextImageView_tiv_text_size, 20);
        int textColor = a.getColor(R.styleable.TextImageView_tiv_text_color, ContextCompat.getColor(context, R.color.colorWhite));
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        bounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int verticalAdjustment = (this.getHeight() - bounds.top) / 2;
        canvas.translate(0, verticalAdjustment);

        float startX = (0 - bounds.left);
        float startY = 0;
        // draw text
        canvas.drawText(text, startX, startY, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.width();
        int height = bounds.height();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthRequirement = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement;
        } else if (widthMode == MeasureSpec.AT_MOST && width > widthRequirement) {
            width = widthRequirement;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightRequirement = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement;
        } else if (heightMode == MeasureSpec.AT_MOST && width > heightRequirement) {
            height = heightRequirement;
        }

        setMeasuredDimension(width, height);
    }
}
