package org.zzy.largeimage;
import android.util.Log;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.List;

/**
 * Created by Yan.binbin on 2020/6/24
 * Desc:
 */
public class LargeImageMonitorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        // 如果是release版本，则不进行字节码替换
        List<String> taskNames = project.getGradle().getStartParameter().getTaskNames();
        for (String taskName : taskNames) {
            Log.w("bb", "taskName = " + taskName);
            if (taskName.contains("Release")) {
                return;
            }
        }

        // 创建自定义扩展，在gradle中配置开关
        project.getExtensions().create("largeImageMonitor", LargeImageExtension.class);
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                LargeImageExtension extension = project.getExtensions().getByType(LargeImageExtension.class);
                Config.getInstance().init(extension);
            }
        });

        // 将自定义Transform添加到编译流程中
        AppExtension appExtension = (AppExtension) project.getProperties().get("android");
        appExtension.registerTransform();

    }
}
