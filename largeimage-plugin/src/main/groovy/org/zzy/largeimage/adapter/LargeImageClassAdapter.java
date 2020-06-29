package org.zzy.largeimage.adapter;

import android.util.Log;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.zzy.largeimage.Config;
import org.zzy.largeimage.method.GlideMethodAdapter;

/**
 * Created by Yan.binbin on 2020/6/28
 * Desc:
 */
public class LargeImageClassAdapter extends ClassVisitor {
    private static final String IMAGELOADER_METHOD_NAME_DESC = "(Ljava/lang/String;Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/assist/ImageSize;Lcom/nostra13/universalimageloader/core/listener/ImageLoadingListener;Lcom/nostra13/universalimageloader/core/listener/ImageLoadingProgressListener;)V";

    // 当前类名
    private String className;

    public LargeImageClassAdapter(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, methodName, desc, signature, exceptions);

        // 如果插件开关关闭，则不插入字节码
        if (!Config.getInstance().isLargeImagePluginSwitch()) {
            return mv;
        }

        // 对glide4.11版本的SingleRequest类的构造方法进行字节码揭盖
        if (className.equals("com/bumptech/glide/request/SingleRequest") && methodName.equals("<init>") && desc != null) {
            log(className, access, methodName, desc, signature);
            return mv == null ? null : new GlideMethodAdapter(mv, access, methodName, desc);
        }
        return mv;
    }

    private void log(String className, int access, String name, String desc, String signature) {
        Log.w("bb", "LargeImageClassAdapter===matched====>" + "  className===" + className + "   access===" + access + "   methodName===" + name + "   desc===" + desc + "   signature===" + signature);
    }
}
