package ru.projectx.clicker.utils;

public class LogUtils {

    public static void info(String msg, Object ... args) {
        for(Object arg : args) msg = msg.replaceFirst("%s", arg.toString());
        System.out.println(msg);
    }
}
