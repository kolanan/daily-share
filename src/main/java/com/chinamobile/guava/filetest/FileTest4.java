package com.chinamobile.guava.filetest;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTest4 {

    public static void main(String[] args) throws IOException {
        String pattern = "=.*?/";
        String pattern2 = "@5=.*?/";
        Pattern r = Pattern.compile(pattern);
        Pattern r2 = Pattern.compile(pattern2);
        List<List<String>> result = Lists.newLinkedList();
        List<String> list = Lists.newLinkedList();
        int k = 0;
        int total = 0;
        LineIterator lineIterator = FileUtils.lineIterator(new File("D://t_checkin_record-12.txt"), "utf-8");
        boolean isTime = true;
        String localdate = null;
        boolean flag = true;
        while (lineIterator.hasNext()) {
            System.out.println(k);
            String s = lineIterator.nextLine();
            if (s.contains("end_log_pos")) {
                try {
                    localdate = s.substring(1, 16);
                    list.add(DateUtil.parse("20" + localdate, "yyyyMMdd HH:mm:ss").toString());
                } catch (Exception e) {
                    continue;
                }
                flag = true;
                isTime = true;

//                System.out.println(substring);
            }
            Matcher m = r.matcher(s);
            Matcher m2 = r2.matcher(s);
            // 有匹配到
            if (m.find()) {
                String num = m.group(0).replace("=", "").replace("/", "").trim();
                list.add(num);
                k = k + 1;
                if (m2.find()) {
                    try {
                        Date parse1 = DateUtil.parse("20" + localdate, "yyyyMMdd HH:mm:ss");
                        Date parse2 = DateUtil.parse(num.replace("'", ""), "yyyy-MM-dd HH:mm:ss");
                        long between = DateUtil.between(parse1, parse2, DateUnit.MINUTE, true);
                        if (between >= 5) {
                            flag = false;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }

                if (k != 0 && k % 13 == 0) {
                    if(!isTime) {
                        list.add(0,DateUtil.parse("20" + localdate, "yyyyMMdd HH:mm:ss").toString());
                    }
                    List<String> strings1 = ObjectUtil.cloneByStream(list);
                    if (!flag) {
                        result.add(strings1);
                        total++;
                        System.out.println("符合条件__" + total);
                        if (total % 300000 == 0) {
                            BigExcelWriter writer = ExcelUtil.getBigWriter("d:/xxx" + total + ".xlsx");
// 一次性写出内容，使用默认样式
                            writer.write(result);
// 关闭writer，释放内存
                            writer.close();
                            result = new LinkedList<>();
                        }
                    }
                    list = new LinkedList<>();
                    isTime = false;
                    k = 0;
                }

            }
        }
        BigExcelWriter writer = ExcelUtil.getBigWriter("d:/xxx" + total + ".xlsx");
// 一次性写出内容，使用默认样式
        writer.write(result);
// 关闭writer，释放内存
        writer.close();

    }
}
