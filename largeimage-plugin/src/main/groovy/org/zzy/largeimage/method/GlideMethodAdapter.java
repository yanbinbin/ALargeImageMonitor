package org.zzy.largeimage.method;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * Created by Yan.binbin on 2020/6/28
 * Desc: 对SingleRequest类中的构造方法进行字节码修改，构造方法会对SingleRequest类进行初始化，其中有一个
 * requestListeners参数，它是一个List类型，在图片准备完毕时会对该List进行遍历回调，我们只需要在该List中添加
 * 我们自定义的listener，遍历时就会回调我们的方法，从而拿到图片数据
 */
public class GlideMethodAdapter extends AdviceAdapter {
    public GlideMethodAdapter(MethodVisitor mv, int access, String name, String desc) {
        super(Opcodes.ASM5, mv, access, name, desc);
    }

    /**
     * 方法退出时
     * 1、先拿到requestListeners
     * 2、然后将其进行修改
     * 3、将修改后的requestListeners设置回去
     */
    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "com/bumptech/glide/request/SingleRequest", "requestListeners", "Ljava/util/List;");
        mv.visitMethodInsn(INVOKESTATIC, "org/zzy/lib/largeimage/aop/glide/GlideHook", "process", "(Ljava/util/List;)V", false);
    }
}
