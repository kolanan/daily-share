package com.chinamobile.guava.daliy;

import cn.hutool.core.util.IdUtil;

import java.io.IOException;

public class Day20210204 {

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 100; i++) {
            System.out.println(getSnowFake());
        }
    }

    public static Long getSnowFake() {
        return IdUtil.getSnowflake(1, 1).nextId();
    }


}
