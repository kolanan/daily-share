package com.chinamobile.guava.filetest;


import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTest {

    public static void main(String[] args) {
        String pattern = "=.*?/";
        Pattern r = Pattern.compile(pattern);
        FileReader fileReader = new FileReader("t_checkin_record-12.txt");
        List<String> strings = fileReader.readLines();
        System.out.println("总条数" + strings.size());
        int k = 0;
        List<List<String>> result = Lists.newArrayList();
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < strings.size(); i++) {
            Matcher m = r.matcher(strings.get(i));
            // 有匹配到
            if (m.find()) {
                String num = m.group(0).replace("=", "").replace("/", "").trim();
                list.add(num);
                k = k + 1;
                if (k != 0 && k % 13 == 0) {
                    List<String> strings1 = ObjectUtil.cloneByStream(list);
                    result.add(strings1);
                    list = new ArrayList<>();
                }
            }
            System.out.println(k);
        }
        BigExcelWriter writer = ExcelUtil.getBigWriter("d:/xxx.xlsx");

// 一次性写出内容，使用默认样式
        writer.write(result);
// 关闭writer，释放内存
        writer.close();
    }
}
