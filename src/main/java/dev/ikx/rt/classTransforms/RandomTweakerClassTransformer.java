package dev.ikx.rt.classTransforms;

import dev.ikx.rt.classTransforms.mods.astralsorcery.ASMTileAttunementAltar;
import dev.ikx.rt.classTransforms.mods.tconstruct.ASMAbstractMaterialSectionTransformer;
import dev.ikx.rt.classTransforms.vanilla.ASMItemStack;
import dev.ikx.rt.impl.internal.config.RTConfig;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.ASM5;

public class RandomTweakerClassTransformer implements IClassTransformer {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final boolean debug = FMLLaunchHandler.isDeobfuscatedEnvironment();

    protected static ClassWriter createClassWriter(@SuppressWarnings("SameParameterValue") boolean debug) {
        return new RandomTweakerClassWriter(debug ? 0 : ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    }

    protected static ClassWriter createDefaultClassWriter(@SuppressWarnings("SameParameterValue") boolean debug) {
        return new ClassWriter(debug ? 0 : ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    }

    protected static byte[] tryGetAsmResult(
            String modName,
            String className,
            byte[] basicClass,
            ClassVisitor visitor,
            ClassWriter writer
    ) {
        try {
            LOGGER.info("On {} class visitor", modName);
            ClassReader classReader = new ClassReader(basicClass);
            classReader.accept(visitor, 0);
            byte[] toReturn = writer.toByteArray();
            if (debug) {
                FileUtils.writeByteArrayToFile(FileUtils.getFile("asmResult", modName, className + ".class"), toReturn);
            }
            return toReturn;
        } catch (Exception e) {
            LOGGER.error("Error loading {} ASM", modName, e);
        }
        return basicClass;
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName.startsWith("dev.ikx.rt"))
            return basicClass;
        if (RTConfig.Astralsorcery.attunementModification && "hellfirepvp.astralsorcery.common.tile.TileAttunementAltar".equals(transformedName)) {
            LOGGER.info("transforming class {} ({})", transformedName, name);
            ClassWriter classWriter = createClassWriter(false);
            ASMTileAttunementAltar asm = new ASMTileAttunementAltar(ASM5, classWriter);
            return tryGetAsmResult("astral sorcery", transformedName, basicClass, asm, classWriter);
        }
        if (RTConfig.Tconstruct.iconModification && "slimeknights.tconstruct.library.book.sectiontransformer.AbstractMaterialSectionTransformer".equals(transformedName)) {
            LOGGER.info("transforming class {} ({})", transformedName, name);
            ClassWriter classWriter = createClassWriter(false);
            ASMAbstractMaterialSectionTransformer asm = new ASMAbstractMaterialSectionTransformer(ASM5, classWriter);
            return tryGetAsmResult("tinkers construct", transformedName, basicClass, asm, classWriter);
        }
        if (RTConfig.RandomTweaker.itemAttributeModification && "net.minecraft.item.ItemStack".equals(transformedName)) {
            LOGGER.info("transforming class {} ({})", transformedName, name);
            ClassWriter classWriter = createDefaultClassWriter(false);
            ASMItemStack asm = new ASMItemStack(ASM5, classWriter, name);
            return tryGetAsmResult("vanilla", transformedName, basicClass, asm, classWriter);
        }
        return basicClass;
    }

}
