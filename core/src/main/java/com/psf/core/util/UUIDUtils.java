package com.psf.core.util;

import java.util.UUID;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
