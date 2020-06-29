package org.zzy.largeimage.weaver;

import com.quinn.hunter.transform.asm.BaseWeaver;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.zzy.largeimage.adapter.LargeImageClassAdapter;

/**
 * Created by Yan.binbin on 2020/6/28
 * Desc:
 */
public class LargeImageWeaver extends BaseWeaver {
    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new LargeImageClassAdapter(classWriter);
    }
}
