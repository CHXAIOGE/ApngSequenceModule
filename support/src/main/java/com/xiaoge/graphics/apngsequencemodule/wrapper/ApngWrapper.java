package com.xiaoge.graphics.apngsequencemodule.wrapper;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


import com.xiaoge.graphics.animategraphics.core.wrapper.DrawableHelper;
import com.xiaoge.graphics.animategraphics.core.wrapper.DrawableWrapper;
import com.xiaoge.graphics.animategraphics.core.wrapper.PlayListener;
import com.xiaoge.graphics.apngsequence.apngdrawable.ApngDrawable;
import com.xiaoge.graphics.apngsequence.apngdrawable.ApngPlayListener;

import java.io.File;

import static com.xiaoge.graphics.animategraphics.core.wrapper.DrawableHelper.REPEAT_MODE_INFINITE;
import static com.xiaoge.graphics.apngsequence.utils.ApngHelper.getLocalBitmap;


/**
 * Created by zhanglei on 2017/9/11.
 */

public class ApngWrapper implements DrawableWrapper, ApngPlayListener {

    private Drawable loadedImage;

    private PlayListener playListener;

    private File file;

    public ApngWrapper(Drawable loadedImage, File file) {
        this.loadedImage = loadedImage;
        this.file = file;
        if(loadedImage instanceof ApngDrawable) {
            ((ApngDrawable) loadedImage).setPlayListener(this);
        }
    }

    @Override
    public Drawable getDrawable() {
        return loadedImage;
    }

    @Override
    public void start() {
        if(loadedImage == null) {
            return;
        }

        if(loadedImage instanceof ApngDrawable) {
            ((ApngDrawable) loadedImage).start();
        }

    }

    @Override
    public void stop() {
        if(loadedImage == null) {
            return;
        }

        if(loadedImage instanceof ApngDrawable) {
            ((ApngDrawable) loadedImage).stop();
        }
    }

    @Override
    public void setRepeatCount(int count) {
        if(loadedImage == null) {
            return;
        }

        if(loadedImage instanceof ApngDrawable) {
            ((ApngDrawable) loadedImage).setNumPlays(count);
        }
    }

    @Override
    public void setLoopMode(@DrawableHelper.REPEAT_MODE int count) {
        if(count == REPEAT_MODE_INFINITE) {
            if(loadedImage instanceof ApngDrawable) {
                ((ApngDrawable) loadedImage).setNumPlays(REPEAT_MODE_INFINITE);
            }
        }
    }

    @Override
    public void setPlayListener(PlayListener playListener) {
        this.playListener = playListener;
    }

    @Override
    public Bitmap getFirstFrame() {
        return getLocalBitmap(file.getAbsolutePath());
    }

    @Override
    public void onAnimationStart(ApngDrawable apngDrawable) {

    }

    @Override
    public void onAnimationEnd(ApngDrawable apngDrawable) {
        if(playListener != null) {
            playListener.onPlayFinished();
        }
    }

    @Override
    public void onAnimationRepeat(ApngDrawable apngDrawable) {

    }
}
