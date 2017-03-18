package com.sample.instantsonar.tracks.ui;

import com.sample.instantsonar.tracks.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class WaveformView extends RelativeLayout implements Target {

    private ImageView backgroundOne;
    private ImageView backgroundTwo;

    public WaveformView(Context context) {
        super(context);
        init(context);
    }

    public WaveformView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WaveformView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        backgroundOne = constructBackgroundImageView(context);
        backgroundTwo = constructBackgroundImageView(context);

        addView(backgroundOne);
        addView(backgroundTwo);
    }

    private ImageView constructBackgroundImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return imageView;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        Drawable drawable = new BitmapDrawable(getResources(), addGradient(bitmap));
        backgroundOne.setImageDrawable(drawable);
        backgroundTwo.setImageDrawable(drawable);

        startAnimation();
    }

    private Bitmap addGradient(Bitmap originalBitmap) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        Bitmap updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(updatedBitmap);

        Paint transparencyPaint = new Paint();
        transparencyPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        canvas.drawBitmap(originalBitmap, 0, 0, transparencyPaint);

        int gradientTopColor = ContextCompat.getColor(getContext(), R.color.light_orange);
        int gradientBottomColor = ContextCompat.getColor(getContext(), R.color.dark_orange);
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, height,
                                                   gradientTopColor, gradientBottomColor, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        paint.setAlpha(175);
        canvas.drawRect(0, 0, width, height, paint);

        return updatedBitmap;
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        // no background should be set on error
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        // no placeholder should be set while loading
    }

    private void startAnimation() {
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX - width);
                backgroundTwo.setTranslationX(translationX);
            }
        });
        animator.start();
    }
}
