package org.zzy.largeimage;
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
        List<String> taskNames = project.getGradle().getStartParameter().getTaskNames();
        // 如果是
    }
}
