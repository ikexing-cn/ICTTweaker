package dev.ikx.rt.api.internal.file;

import crafttweaker.api.item.IIngredient;
import org.apache.commons.lang3.StringUtils;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@ZenClass("mods.randomtweaker.file.IProp")
public abstract class IProp {

    public static String FILE = getPath(System.getProperty("user.dir"), "rt.properties");

    @ZenMethod
    public static void write(String key, String value) {
        Properties prop = new Properties();
        try (FileReader fr = new FileReader(FILE)) {
            prop.load(fr);
            prop.setProperty(key, value);
            try (FileWriter fw = new FileWriter(FILE)) {
                prop.store(fw, null);
            }
        } catch (IOException ignored) {
        }
    }

    @ZenMethod
    public static void write(String key, int value) {
        write(key, String.valueOf(value));
    }

    @ZenMethod
    public static void write(String key, float value) {
        write(key, String.valueOf(value));
    }

    @ZenMethod
    public static void write(String key, double value) {
        write(key, String.valueOf(value));
    }

    @ZenMethod
    public static void write(String key, long value) {
        write(key, String.valueOf(value));
    }

    @ZenMethod
    public static void write(String key, boolean value) {
        write(key, String.valueOf(value));
    }

    @ZenMethod
    public static void write(String key, IIngredient value) {
        write(key, value.toCommandString());
    }

    @ZenMethod
    public static String read(String key) {
        Properties prop = new Properties();
        try (FileReader fr = new FileReader(FILE)) {
            prop.load(fr);
        } catch (IOException ignored) {
        }
        return prop.getProperty(key);
    }

    @ZenMethod
    public static List<String> getAllKeys() {
        Properties prop = new Properties();
        try (FileReader fr = new FileReader(FILE)) {
            prop.load(fr);
        } catch (IOException ignored) {
        }
        return prop.keySet().stream().map(String.class::cast).collect(Collectors.toList());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isRegister(boolean flag) {
        File file = new File(FILE);
        if (flag) {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ignored) {
                }
            }
        } else {
            if (file.exists()) {
                file.delete();
            }
        }
        return flag;
    }

    public static String getPath(String... path) {
        StringBuilder sb = new StringBuilder();
        for (String s1 : path) {
            if (!StringUtils.isBlank(sb.toString()))
                sb.append(sb.toString().endsWith(File.separator) ? s1 : (File.separator + s1));
            else sb.append(s1).append(File.separator);
        }
        return sb.toString();
    }

}
