package com.pantha.centercropvideo;

import android.content.Context;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Created by gokarna on 8/3/17.
 * center crop video for android
 */

@SuppressWarnings("DanglingJavadoc")
public class CenterCropTextureView extends TextureView {


    public CenterCropTextureView(Context context) {
        super(context);
    }

    public CenterCropTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CenterCropTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void centerCrop(MediaPlayer mediaPlayer, int viewWidth, int viewHeight) {
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        float scaleX = 1, scaleY = 1;
        float videoProportion = (float) videoWidth / (float) videoHeight;
        float screenProportion = (float) viewWidth / (float) viewHeight;
        android.view.ViewGroup.LayoutParams lp =getLayoutParams();
        if (videoWidth > viewWidth && videoHeight < viewHeight) {
            scaleX = videoProportion / screenProportion;
        } else if (videoWidth < viewWidth && videoHeight > viewHeight) {
            scaleY = screenProportion / videoProportion;
        } else if (viewHeight > videoHeight && viewWidth > videoWidth) {
            scaleX = viewHeight / videoHeight;
            scaleY = viewWidth / videoWidth;
        } else if (videoHeight > viewHeight && videoWidth > viewWidth) {
            scaleX = videoProportion;
            scaleY = screenProportion;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY);
        setTransform(matrix);
        setLayoutParams(lp);
    }
}
