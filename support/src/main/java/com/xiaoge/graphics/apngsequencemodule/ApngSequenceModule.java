package com.xiaoge.graphics.apngsequencemodule;


import com.xiaoge.graphics.animategraphics.core.Graphics;
import com.xiaoge.graphics.apngsequence.utils.ApngHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhanglei on 2017/9/16.
 */

public class ApngSequenceModule implements Graphics.Module {

    public ApngSequenceModule() {

    }

    @Override
    public boolean isSupport(File file) {
        return ApngHelper.isAPNG(file);
    }

    @Override
    public boolean isSupport(InputStream inputStream) {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1 ) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            inputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // 打开一个新的输入流
        InputStream is1 = new ByteArrayInputStream(baos.toByteArray());

        return ApngHelper.isAPNG(is1);
    }
}
