package com.chinamobile.guava.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;

public class FileTest {

    public static void main(String[] args) {
        File[] ls = FileUtil.ls("D:");
        for(File file: ls) {
            System.out.println(StrUtil.str(file.getName().getBytes(), CharsetUtil.UTF_8));
        }
    }

}
