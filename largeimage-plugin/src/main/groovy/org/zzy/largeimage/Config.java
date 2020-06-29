package org.zzy.largeimage;

/**
 * Created by Yan.binbin on 2020/6/28
 * Desc:
 */
public class Config {
    /**
     * 大图检测插件开关
     */
    private boolean largeImagePluginSwitch = true;

    private Config() {}

    private static class Holder {
        private static Config INSTANCE = new Config();
    }
    public static Config getInstance() {
        return Holder.INSTANCE;
    }

    public boolean isLargeImagePluginSwitch() {
        return largeImagePluginSwitch;
    }

    public void init(LargeImageExtension extension) {
        if (extension != null) {
            largeImagePluginSwitch = extension.largeImagePluginSwitch;
        }
    }
}
