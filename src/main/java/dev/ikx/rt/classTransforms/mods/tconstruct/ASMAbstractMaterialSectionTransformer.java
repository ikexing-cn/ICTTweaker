package dev.ikx.rt.classTransforms.mods.tconstruct;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Objects;

public class ASMAbstractMaterialSectionTransformer extends ClassVisitor {

    public ASMAbstractMaterialSectionTransformer(int api, ClassVisitor cv) {
        super(api, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (name.equals("transform")) {
            return new ASMAbstractMaterialSectionTransformerTransform(api, super.visitMethod(access, name, desc, signature, exceptions));
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

}

class ASMAbstractMaterialSectionTransformerTransform extends MethodVisitor implements Opcodes {

    private int iconIndex;
    private int materialIndex;
    private int materialListIndex;
    private int materialListLoadIndex = 0;
    private boolean foundLoop = false;
    private boolean foundIcon = false;
    private boolean foundMaterial = false;
    private boolean foundMaterialList = false;
    private Label loopLabel = new Label();

    public ASMAbstractMaterialSectionTransformerTransform(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        if (opcode == INVOKEINTERFACE && owner.equals("java/util/List") && name.equals("iterator") && desc.equals("()Ljava/util/Iterator;") && itf) {
            this.foundLoop = true;
        }
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        if (opcode == CHECKCAST) {
            if (Objects.equals(type, "slimeknights/tconstruct/library/materials/Material")) {
                this.foundMaterial = true;
            }
            if (Objects.equals(type, "java/util/List")) {
                this.foundMaterialList = true;
            }
        }
        if (opcode == NEW) {
            this.foundIcon = true;
        }
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        if (this.foundMaterialList && opcode == ALOAD && var == this.materialListIndex) {
            if (++this.materialListLoadIndex == 2) {
                super.visitVarInsn(ALOAD, this.materialListIndex);
                super.visitMethodInsn(INVOKESTATIC, "dev/ikx/rt/classTransforms/mods/tconstruct/AbstractMaterialSectionTransformerHooks", "sortMaterialList", "(Ljava/util/List;)Ljava/util/List;", false);
                super.visitVarInsn(ASTORE, this.materialListIndex);
                this.foundMaterialList = false;
            }
        }
        super.visitVarInsn(opcode, var);
        if (this.foundMaterial) {
            this.materialIndex = var;
        }
        if (this.foundIcon && opcode == ASTORE) {
            this.iconIndex = var;
        }
        if (this.foundMaterialList && opcode == ASTORE) {
            this.materialListIndex = var;
        }
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }

    @Override
    public void visitLabel(Label label) {
        if (this.foundIcon) {
            this.foundIcon = false;
            super.visitVarInsn(ALOAD, this.materialIndex);
            super.visitMethodInsn(INVOKESTATIC, "dev/ikx/rt/classTransforms/mods/tconstruct/AbstractMaterialSectionTransformerHooks", "isMaterialInShowItemMap", "(Lslimeknights/tconstruct/library/materials/Material;)Z", false);
            Label elseLabel = new Label();
            super.visitJumpInsn(IFEQ, elseLabel);
            super.visitVarInsn(ALOAD, this.materialIndex);
            super.visitMethodInsn(INVOKESTATIC, "dev/ikx/rt/classTransforms/mods/tconstruct/AbstractMaterialSectionTransformerHooks", "createElementItem", "(Lslimeknights/tconstruct/library/materials/Material;)Lslimeknights/mantle/client/gui/book/element/ElementItem;", false);
            super.visitVarInsn(ASTORE, this.iconIndex);
            super.visitLabel(elseLabel);
        }

        super.visitLabel(label);

        if (this.foundLoop) {
            this.loopLabel = label;
            this.foundLoop = false;
        }
        if (this.foundMaterial) {
            this.foundMaterial = false;
            super.visitVarInsn(ALOAD, this.materialIndex);
            super.visitMethodInsn(INVOKESTATIC, "dev/ikx/rt/classTransforms/mods/tconstruct/AbstractMaterialSectionTransformerHooks", "isMaterialInHiddenItems", "(Lslimeknights/tconstruct/library/materials/Material;)Z", false);
            Label label1 = new Label();
            super.visitJumpInsn(IFEQ, label1);
            super.visitJumpInsn(GOTO, this.loopLabel);
            super.visitLabel(label1);
        }
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);
    }
}
