package org.zzy.lib.largeimage.aop.glide;

import com.bumptech.glide.request.RequestListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan.binbin on 2020/6/28
 * Desc:
 */
public class GlideHook {
    public static void process(List<RequestListener> requestListeners) {
        if (requestListeners == null) {
            requestListeners = new ArrayList<>();
        }
        requestListeners.add(new GlideLargeImageListener());
    }
}
