package org.zzy.largeimage.transform;

import com.quinn.hunter.transform.HunterTransform;

import org.gradle.api.Project;
import org.zzy.largeimage.weaver.LargeImageWeaver;

/**
 * Created by Yan.binbin on 2020/6/28
 * Desc:
 */
public class LargeImageTransform extends HunterTransform {

    public LargeImageTransform(Project project) {
        super(project);
        this.bytecodeWeaver = new LargeImageWeaver();
    }

}
