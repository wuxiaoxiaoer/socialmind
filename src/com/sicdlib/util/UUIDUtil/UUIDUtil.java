package com.sicdlib.util.UUIDUtil;

import java.util.UUID;

/**
 * Created by haoyang on 2017/4/20.
 */
public class UUIDUtil {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str;
    }
}
