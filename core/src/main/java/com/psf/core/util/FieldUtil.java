package com.psf.core.util;

import java.lang.reflect.Field;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class FieldUtil {

    public static boolean hasNotNullFiled(Object obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(obj) != null) {
                return true;
            }
        }
        return false;
    }
}
