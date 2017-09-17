package com.xiaoge.graphics.apngsequencemodule;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.xiaoge.graphics.animategraphics.core.loader.GraphicsLoader;
import com.xiaoge.graphics.animategraphics.core.target.Target;
import com.xiaoge.graphics.apngsequence.apngdrawable.ApngImageLoadingListener;
import com.xiaoge.graphics.apngsequence.utils.ApngHelper;
import com.xiaoge.graphics.apngsequencemodule.wrapper.ApngWrapper;

import java.io.File;
import java.io.InputStream;

import static com.xiaoge.graphics.apngsequence.apngdrawable.ApngDrawable.TAG;


/**
 * Created by zhanglei on 2017/9/10.
 *
 */

public class ApngSupportLoader extends GraphicsLoader {

    public ApngSupportLoader(File data, Target target) {
        super(data, target);
    }

    public ApngSupportLoader(InputStream stream, Target target) {
        super(stream, target);
    }

    @Override
    public void into(final ImageView view) {
        if(stream != null) {
            ApngHelper.loadAnimatedPng(view, stream, new ApngImageLoadingListener(null) {
                public void onLoadingComplete(String imageUri, ImageView imageView, Drawable loadedImage) {
                    super.onLoadingComplete(imageUri, imageView, loadedImage);
                    Log.d(TAG, "onLoadingComplete");
                    view.setImageDrawable(loadedImage);

                    if(target != null) {
                        target.onResourceReady(new ApngWrapper(loadedImage, file));
                    }
                }

                public void onLoadFailed(String imageUri, ImageView imageView) {
                    Log.d(TAG, "onLoadFailed");
                    if(target != null) {
                        target.onLoadFailed();
                    }
                }
            });
        } else {
            ApngHelper.loadAnimatedPng(view, file.getAbsolutePath(), new ApngImageLoadingListener(null) {
                public void onLoadingComplete(String imageUri, ImageView imageView, Drawable loadedImage) {
                    super.onLoadingComplete(imageUri, imageView, loadedImage);
                    Log.d(TAG, "onLoadingComplete");
                    view.setImageDrawable(loadedImage);

                    if(target != null) {
                        target.onResourceReady(new ApngWrapper(loadedImage, file));
                    }
                }

                public void onLoadFailed(String imageUri, ImageView imageView) {
                    Log.d(TAG, "onLoadFailed");
                    if(target != null) {
                        target.onLoadFailed();
                    }
                }
            });
        }
    }

}
