package com.chinamobile.guava.filetest;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTest2 {

    public static void main(String[] args) throws IOException {
        String pattern = "=.*?/";
        Pattern r = Pattern.compile(pattern);
        List<List<String>> result = Lists.newArrayList();
        List<String> list = Lists.newArrayList();
        int k = 0;
        LineIterator lineIterator = FileUtils.lineIterator(new File("D://t_checkin_record-12.txt"), "utf-8");
        String localdate = null;
        boolean flag = true;
        while (lineIterator.hasNext()) {
            System.out.println(k);
            String s = lineIterator.nextLine();
            if (s.contains("end_log_pos")) {
                try {
                    String substring = s.substring(1, 16);
                    localdate = substring;
                    list.add(DateUtil.parse("20" + localdate, "yyyyMMdd HH:mm:ss").toString());
                } catch (Exception e) {
                    continue;
                }
                flag = true;
//                System.out.println(substring);
            }
            Matcher m = r.matcher(s);
            // 有匹配到
            if (m.find()) {
                String num = m.group(0).replace("=", "").replace("/", "").trim();
                list.add(num);
                k = k + 1;
                if (k == 5) {

                    try {
                        Date parse1 = DateUtil.parse("20" + localdate, "yyyyMMdd HH:mm:ss");
                        Date parse2 = DateUtil.parse(num.replace("'", ""), "yyyy-MM-dd HH:mm:ss");
                        long between = DateUtil.between(parse1, parse2, DateUnit.MINUTE, true);
                        if (between < 5) {
                            flag = false;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }

                if (k != 0 && k % 7 == 0) {
//                    List<String> strings1 = ObjectUtil.cloneByStream(list);
                    if (flag) {
                        result.add(list);
                    }
                    list = new ArrayList<>();
                    k = 0;
                }

            }
        }


        BigExcelWriter writer = ExcelUtil.getBigWriter("d:/xxx3.xlsx");
// 一次性写出内容，使用默认样式
        writer.write(result);
// 关闭writer，释放内存
        writer.close();
    }
}
