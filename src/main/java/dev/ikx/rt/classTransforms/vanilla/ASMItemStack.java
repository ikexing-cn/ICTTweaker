package dev.ikx.rt.classTransforms.vanilla;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author youyihj
 */
public class ASMItemStack extends ClassVisitor {

    private final String className;

    public ASMItemStack(int api, ClassVisitor cv, String className) {
        super(api, cv);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(className, name, desc);
        if (methodName.equals("func_82840_a") || methodName.equals("getTooltip")) {
            return new ASMItemStackGetTooltip(api, super.visitMethod(access, name, desc, signature, exceptions));
        }
        if (methodName.equals("func_111283_C") || methodName.equals("getAttributeModifiers")) {
            return new ASMItemStackGetAttributeModifiers(api, super.visitMethod(access, name, desc, signature, exceptions));
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}

class ASMItemStackGetTooltip extends MethodVisitor implements Opcodes {

    private int jumpOrdinal = 0;

    public ASMItemStackGetTooltip(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        if (opcode == IF_ACMPNE) {
            if (jumpOrdinal == 1 || jumpOrdinal == 2) {
                super.visitMethodInsn(INVOKESTATIC, "java/util/Objects", "equals", "(Ljava/lang/Object;Ljava/lang/Object;)Z", false);
                super.visitJumpInsn(IFEQ, label);
            } else {
                super.visitJumpInsn(opcode, label);
            }
            jumpOrdinal++;
        } else {
            super.visitJumpInsn(opcode, label);
        }
    }
}

class ASMItemStackGetAttributeModifiers extends MethodVisitor implements Opcodes {

    private boolean triggered = false;

    public ASMItemStackGetAttributeModifiers(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        if (name.equals("getAttributeModifiers")) {
            triggered = true;
        }
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var);
        if (triggered && opcode == ASTORE && var == 2) {
            super.visitVarInsn(ALOAD, 0);
            super.visitVarInsn(ALOAD, 1);
            super.visitVarInsn(ALOAD, 2);
            super.visitMethodInsn(INVOKESTATIC,
                    "dev/ikx/rt/classTransforms/vanilla/ItemStackHook",
                    "fireAttributeModificationEvent",
                    "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/inventory/EntityEquipmentSlot;Lcom/google/common/collect/Multimap;)V",
                    false);
            triggered = false;
        }
    }
}