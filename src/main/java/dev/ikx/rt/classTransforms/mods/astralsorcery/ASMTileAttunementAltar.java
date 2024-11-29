package dev.ikx.rt.classTransforms.mods.astralsorcery;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class ASMTileAttunementAltar extends ClassVisitor {
    public static final String reflectionMethods = "dev/ikx/rt/classTransforms/mods/astralsorcery/AttunementAltarMethodReflections";

    public ASMTileAttunementAltar(int api) {
        super(api);
    }

    public ASMTileAttunementAltar(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {

        if (name.equals("update") || name.equals("func_73660_a")) {

            return new ASMAltarUpdateTransformer(api, super.visitMethod(access, name, desc, signature, exceptions));
        }

        if (name.equals("checkForAttunements")) {

            return new ASMAltarCheckAttunementTransformer(api, super.visitMethod(access, name, desc, signature, exceptions));
        }
        if (name.equals("setAttunementState")) {

            return new ASMAltarSetAttunementStateTransformer(api, super.visitMethod(access, name, desc, signature, exceptions));
        }

        if (name.equals("renderEffects")) {

            return new ASMAltarRenderEffectTransformer(api, super.visitMethod(access, name, desc, signature, exceptions));
        }

        if (name.equals("lambda$playCrystalAttenuationEffects$8")) {

            return new ASMAltarplayCrystalAttenuationEffectsTransformer(api, super.visitMethod(access, name, desc, signature, exceptions));
        }

        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}

class ASMAltarUpdateTransformer extends MethodVisitor {

    static boolean isCraftFinishFlag1 = false;
    static boolean isCraftFinishFlag2 = false;
    static boolean isCraftFinishFlag3 = false;
    static boolean isCraftFinishFlag4 = false;

    static boolean customRecipeInserted1 = false;
    static boolean customRecipeInserted2 = false;
    static int checkItemLogicFoundTime = 0;

    static boolean resultCreated = false;
    static Label logicLabel = new Label();

    static int lastStoredStack = -1;
    static int jumpsFound = 0;

    public ASMAltarUpdateTransformer(int api) {
        super(api);
    }

    public ASMAltarUpdateTransformer(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }


    public void visitJumpInsn(int opcode, Label label) {
        if (opcode == IFNE && (checkItemLogicFoundTime == 1) && (!customRecipeInserted1)) {
            super.visitJumpInsn(opcode, label);
            logicLabel = label;

            //this.activeFound
            super.visitVarInsn(ALOAD, 0);
            super.visitFieldInsn(GETFIELD,
                    "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                    "activeFound",
                    "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;");

            //this.activeEntity
            super.visitVarInsn(ALOAD, 0);
            super.visitFieldInsn(GETFIELD,
                    "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                    "activeEntity",
                    "Lnet/minecraft/entity/Entity;");

            //haveRecipe(this.activeFound, this.activeEntity)
            super.visitMethodInsn(INVOKESTATIC,
                    ASMTileAttunementAltar.reflectionMethods,
                    "haveRecipe",
                    "(Lhellfirepvp/astralsorcery/common/constellation/IConstellation;" +
                            "Lnet/minecraft/entity/Entity;)Z",
                    false);
            super.visitJumpInsn(IFNE, logicLabel);
            customRecipeInserted1 = true;
            return;
        }
        if (opcode == IFEQ && (checkItemLogicFoundTime == 3) && (!customRecipeInserted2)) {
            logicLabel = label;

            jumpsFound += 1;
            if (jumpsFound == 1) {
                //super.visitVarInsn(ASTORE, 11);
                return;
            } else if (jumpsFound >= 2) {
                //super.visitVarInsn(ASTORE, 12);

                //this.activeFound
                super.visitVarInsn(ALOAD, 0);
                super.visitFieldInsn(GETFIELD,
                        "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                        "activeFound",
                        "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;");

                //this.activeEntity
                super.visitVarInsn(ALOAD, 0);
                super.visitFieldInsn(GETFIELD,
                        "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                        "activeEntity",
                        "Lnet/minecraft/entity/Entity;");

                //haveRecipe(this.activeFound, this.activeEntity)
                super.visitMethodInsn(INVOKESTATIC,
                        ASMTileAttunementAltar.reflectionMethods,
                        "haveRecipe",
                        "(Lhellfirepvp/astralsorcery/common/constellation/IConstellation;" +
                                "Lnet/minecraft/entity/Entity;)Z",
                        false);
                //super.visitVarInsn(ALOAD, 11);
                //super.visitVarInsn(ALOAD, 12);

                //logic patch just take 3 boolean and do (A&&B)||C.
                //This is because it's hard to insert more than one statement
                //into hard-coded boolean operations
                super.visitMethodInsn(INVOKESTATIC,
                        ASMTileAttunementAltar.reflectionMethods,
                        "logicPatch",
                        "(ZZZ)Z",
                        false);
                super.visitJumpInsn(IFEQ, logicLabel);
                customRecipeInserted2 = true;
                return;
            }
        }
        super.visitJumpInsn(opcode, label);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        if (opcode == ASTORE && resultCreated) {
            lastStoredStack = var;
            resultCreated = false;
        }
        if (opcode == ASTORE && var == lastStoredStack + 1 && isCraftFinishFlag3) {

            isCraftFinishFlag4 = true;
        }
        if (isCraftFinishFlag4) {
            super.visitVarInsn(opcode, var);
            super.visitVarInsn(ALOAD, lastStoredStack);     //new item stack        - first in stack
            super.visitVarInsn(ALOAD, 0);               //"this"
            super.visitFieldInsn(GETFIELD,                  //".activeEntity"
                    "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                    "activeEntity",
                    "Lnet/minecraft/entity/Entity;");
            //cast to EntityItem    - second in stack
            super.visitTypeInsn(CHECKCAST, "net/minecraft/entity/item/EntityItem");
            super.visitVarInsn(ALOAD, 0);
            super.visitFieldInsn(GETFIELD,
                    "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                    "activeFound",
                    "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;");
            //constellation - third in stack
            //invoke our method (current stack: result, original, world, constellation)
            super.visitMethodInsn(INVOKESTATIC,
                    ASMTileAttunementAltar.reflectionMethods,
                    "onCraftingFinish",
                    "(Lnet/minecraft/item/ItemStack;" +
                            "Lnet/minecraft/entity/item/EntityItem;" +
                            "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;)" +
                            "V",
                    false);
            isCraftFinishFlag1 = isCraftFinishFlag2 = isCraftFinishFlag3 = isCraftFinishFlag4 = false;
            return;
        }
        super.visitVarInsn(opcode, var);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        if (opcode == CHECKCAST) {
            if (isCraftFinishFlag1 && type.equals("net/minecraft/entity/item/EntityItem")) {

                isCraftFinishFlag2 = true;
            }
        } else {
            isCraftFinishFlag1 = isCraftFinishFlag2 = isCraftFinishFlag3 = false;
        }
        if (opcode == CHECKCAST && type.equals("hellfirepvp/astralsorcery/common/item/crystal/base/ItemRockCrystalBase")) {
            return; //generify and remove force cast to crystal
        }
        if (opcode == CHECKCAST && type.equals("hellfirepvp/astralsorcery/common/constellation/IWeakConstellation")) {
            return; //generify and remove force cast to crystal
        }
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        switch (opcode) {
            case INVOKESTATIC:
                if (
                        owner.equals("hellfirepvp/astralsorcery/common/util/ItemUtils") &&
                                name.equals("copyStackWithSize") &&
                                desc.equals("(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/item/ItemStack;")
                ) {
                    resultCreated = true;
                }
                if (
                        owner.equals("hellfirepvp/astralsorcery/common/item/crystal/base/ItemTunedCrystalBase") &&
                                name.equals("applyMainConstellation")
                ) {
                    super.visitMethodInsn(INVOKESTATIC,
                            ASMTileAttunementAltar.reflectionMethods,
                            "applyMainConstellation",
                            "(Lnet/minecraft/item/ItemStack;" +
                                    "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;)V",
                            false);
                    return;
                }
                if (
                        owner.equals("hellfirepvp/astralsorcery/common/item/crystal/CrystalProperties") &&
                                name.equals("applyCrystalProperties")
                ) {
                    super.visitMethodInsn(INVOKESTATIC,
                            ASMTileAttunementAltar.reflectionMethods,
                            "applyCrystalProperties",
                            "(Lnet/minecraft/item/ItemStack;" +
                                    "Lhellfirepvp/astralsorcery/common/item/crystal/CrystalProperties;)V",
                            false);
                    return;
                }
                break;
            case INVOKEVIRTUAL:
                if (
                        owner.equals("net/minecraft/entity/item/EntityItem") &&
                                (name.equals("getThrower") || name.equals("func_145800_j")) &&
                                desc.equals("()Ljava/lang/String;")
                ) {
                    isCraftFinishFlag3 = true;
                }
                if (owner.equals("hellfirepvp/astralsorcery/common/item/crystal/base/ItemRockCrystalBase") &&
                        name.equals("getTunedItemVariant")) {
                    super.visitVarInsn(ALOAD, 0);
                    super.visitFieldInsn(GETFIELD,
                            "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                            "activeEntity",
                            "Lnet/minecraft/entity/Entity;");
                    super.visitTypeInsn(CHECKCAST, "net/minecraft/entity/item/EntityItem");

                    super.visitVarInsn(ALOAD, 0);
                    super.visitFieldInsn(GETFIELD,
                            "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                            "activeFound",
                            "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;");

                    super.visitMethodInsn(INVOKESTATIC,
                            ASMTileAttunementAltar.reflectionMethods,
                            "getTunedItemVariant",
                            "(Lnet/minecraft/item/Item;" +
                                    "Lnet/minecraft/entity/item/EntityItem;" +
                                    "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;)" +
                                    "Lnet/minecraft/item/Item;",
                            false);
                    return;
                }
                break;
            case INVOKEINTERFACE:
                if (
                        owner.equals("com/google/common/base/Predicate") &&
                                name.equals("apply") &&
                                desc.equals("(Ljava/lang/Object;)Z")
                ) {
                    checkItemLogicFoundTime += 1;
                }
                break;
            case INVOKESPECIAL:
                if(
                        owner.equals("hellfirepvp/astralsorcery/common/tile/TileAttunementAltar") &&
                                name.equals("setAttunementState") &&
                                desc.equals("(ILnet/minecraft/entity/Entity;)V")
                ){
                    isCraftFinishFlag1 = true;
                }
                break;
            default:
                isCraftFinishFlag1 = isCraftFinishFlag2 = isCraftFinishFlag3 = isCraftFinishFlag4 = false;
        }

        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}

class ASMAltarRenderEffectTransformer extends MethodVisitor {
    static boolean customRecipeInserted = false;
    static boolean checkItemLogicFound = false;
    static Label currentLabel = new Label();
    static Label logicLabel = new Label();

    public ASMAltarRenderEffectTransformer(int api) {
        super(api);
    }

    public ASMAltarRenderEffectTransformer(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        if (opcode == IFNE && checkItemLogicFound && (!customRecipeInserted)) {
            super.visitJumpInsn(opcode, label);
            logicLabel = label;
            super.visitVarInsn(ALOAD, 1);
            super.visitMethodInsn(INVOKESTATIC,
                    ASMTileAttunementAltar.reflectionMethods,
                    "haveAnyRecipe",
                    "(Lnet/minecraft/entity/Entity;)Z",
                    false);
            super.visitJumpInsn(IFNE, logicLabel);
            customRecipeInserted = true;
            return;
        }
        super.visitJumpInsn(opcode, label);
    }


    @Override
    public void visitLabel(Label label) {
        currentLabel = label;
        super.visitLabel(label);
    }


    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        switch (opcode) {
            case INVOKEINTERFACE:
                if (
                        owner.equals("com/google/common/base/Predicate") &&
                                name.equals("apply") &&
                                desc.equals("(Ljava/lang/Object;)Z") &&
                                (!customRecipeInserted)
                ) {

                    checkItemLogicFound = true;
                }
                break;
        }

        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}

class ASMAltarplayCrystalAttenuationEffectsTransformer extends MethodVisitor {
    static boolean customRecipeInserted = false;
    static boolean checkItemLogicFound = false;
    static Label currentLabel = new Label();
    static Label logicLabel = new Label();

    static int jumpsFound = 0;

    public ASMAltarplayCrystalAttenuationEffectsTransformer(int api) {
        super(api);
    }

    public ASMAltarplayCrystalAttenuationEffectsTransformer(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        if (opcode == IFNE && checkItemLogicFound && (!customRecipeInserted)) {
            super.visitJumpInsn(opcode, label);
            logicLabel = label;
            super.visitVarInsn(ALOAD, 1);
            super.visitMethodInsn(INVOKESTATIC,
                    ASMTileAttunementAltar.reflectionMethods,
                    "haveAnyRecipe",
                    "(Lnet/minecraft/entity/Entity;)Z",
                    false);
            super.visitJumpInsn(IFNE, logicLabel);
            customRecipeInserted = true;
            return;
        }
        super.visitJumpInsn(opcode, label);
    }


    @Override
    public void visitLabel(Label label) {
        currentLabel = label;
        super.visitLabel(label);
    }


    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        switch (opcode) {
            case INVOKEINTERFACE:
                if (
                        owner.equals("com/google/common/base/Predicate") &&
                                name.equals("apply") &&
                                desc.equals("(Ljava/lang/Object;)Z") &&
                                (!customRecipeInserted)
                ) {

                    checkItemLogicFound = true;
                }
                break;
        }

        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}


class ASMAltarCheckAttunementTransformer extends MethodVisitor {

    static boolean isCheckItemFlag1 = false;
    static boolean isCheckItemFlag2 = false;
    static boolean isCheckItemFlag3 = false;

    static boolean customRecipeInserted = false;
    static boolean checkItemLogicFound = false;
    static boolean gotLabel = false;
    static Label currentLabel = new Label();
    static Label logicLabel = new Label();

    static int jumpsFound = 0;

    public ASMAltarCheckAttunementTransformer(int api) {
        super(api);
    }

    public ASMAltarCheckAttunementTransformer(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        if (opcode == IFEQ && checkItemLogicFound && (!customRecipeInserted)) {
            logicLabel = label;

            jumpsFound += 1;
            if (jumpsFound == 1) {
                //super.visitVarInsn(ASTORE, 11);
                return;
            } else if (jumpsFound >= 2) {
                //super.visitVarInsn(ASTORE, 12);
                super.visitVarInsn(ALOAD, 0);
                super.visitFieldInsn(GETFIELD,
                        "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                        "activeFound",
                        "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;");
                super.visitVarInsn(ALOAD, 3);
                super.visitMethodInsn(INVOKESTATIC,
                        ASMTileAttunementAltar.reflectionMethods,
                        "haveRecipe",
                        "(Lhellfirepvp/astralsorcery/common/constellation/IConstellation;" +
                                "Lnet/minecraft/entity/item/EntityItem;)Z",
                        false);
                //super.visitVarInsn(ALOAD, 11);
                //super.visitVarInsn(ALOAD, 12);
                super.visitMethodInsn(INVOKESTATIC,
                        ASMTileAttunementAltar.reflectionMethods,
                        "logicPatch",
                        "(ZZZ)Z",
                        false);
                super.visitJumpInsn(IFEQ, logicLabel);
                customRecipeInserted = true;
                return;
            }
        }
        super.visitJumpInsn(opcode, label);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        switch (opcode) {
            case CHECKCAST:
                if (isCheckItemFlag1 && type.equals("net/minecraft/entity/item/EntityItem")) {

                    isCheckItemFlag2 = true;
                }
                break;
        }
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitLabel(Label label) {
        currentLabel = label;
        super.visitLabel(label);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        switch (opcode) {
            case INVOKEINTERFACE:

                if (
                        owner.equals("java/util/List") &&
                                name.equals("get") &&
                                desc.equals("(I)Ljava/lang/Object;")
                ) {

                    isCheckItemFlag1 = true;
                }
                if (
                        owner.equals("com/google/common/base/Predicate") &&
                                name.equals("apply") &&
                                desc.equals("(Ljava/lang/Object;)Z") &&
                                (!customRecipeInserted)
                ) {

                    checkItemLogicFound = true;
                }
                break;
            default:
                isCheckItemFlag1 = isCheckItemFlag2 = isCheckItemFlag3 = false;
        }

        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}

class ASMAltarSetAttunementStateTransformer extends MethodVisitor {

    static boolean isSetAttunementFlag1 = false;
    static boolean isSetAttunementFlag2 = false;
    static boolean isSetAttunementFlag3 = false;

    public ASMAltarSetAttunementStateTransformer(int api) {
        super(api);
    }

    public ASMAltarSetAttunementStateTransformer(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }


    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        switch (opcode) {
            case PUTFIELD:

                if (isSetAttunementFlag1 &&
                        owner.equals("hellfirepvp/astralsorcery/common/tile/TileAttunementAltar") &&
                        name.equals("entityIdActive") &&
                        desc.equals("I")
                ) {

                    isSetAttunementFlag2 = true;
                }

                if (isSetAttunementFlag2 &&
                        owner.equals("hellfirepvp/astralsorcery/common/tile/TileAttunementAltar") &&
                        name.equals("activeEntity") &&
                        desc.equals("Lnet/minecraft/entity/Entity;")
                ) {

                    isSetAttunementFlag3 = true;
                }


                if (isSetAttunementFlag3) {
                    super.visitFieldInsn(opcode, owner, name, desc);

                    //insert after

                    //load from local 0 ("this")
                    super.visitVarInsn(ALOAD, 0);
                    //get the active entity
                    super.visitFieldInsn(GETFIELD,
                            "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                            "activeEntity",
                            "Lnet/minecraft/entity/Entity;");

                    super.visitVarInsn(ALOAD, 0);
                    //get the Constellation
                    super.visitFieldInsn(GETFIELD,
                            "hellfirepvp/astralsorcery/common/tile/TileAttunementAltar",
                            "activeFound",
                            "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;");

                    //invoke our method
                    super.visitMethodInsn(INVOKESTATIC,
                            ASMTileAttunementAltar.reflectionMethods,
                            "onAttunementStart",
                            "(Lnet/minecraft/entity/Entity;" +
                                    "Lhellfirepvp/astralsorcery/common/constellation/IConstellation;)" +
                                    "V",
                            false);
                    isSetAttunementFlag1 = isSetAttunementFlag2 = isSetAttunementFlag3 = false;
                    return;
                }

        }
        super.visitFieldInsn(opcode, owner, name, desc);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        switch (opcode) {
            case INVOKEVIRTUAL:

                if (
                        owner.equals("net/minecraft/entity/Entity") &&
                                (name.equals("getEntityId") || name.equals("func_145782_y")) &&
                                desc.equals("()I")
                    //"trigger.getEntityId()"
                ) {

                    isSetAttunementFlag1 = true;
                }
                break;
            default:
                isSetAttunementFlag1 = isSetAttunementFlag2 = isSetAttunementFlag3 = false;
        }

        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}