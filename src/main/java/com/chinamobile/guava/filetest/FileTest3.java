package com.chinamobile.guava.filetest;


import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTest3 {

    public static void main(String[] args) throws IOException {
        String pattern = "=.*?/";
        Pattern r = Pattern.compile(pattern);
//        FileReader fileReader = new FileReader("t_checkin_record-12.txt");
//        List<String> strings = fileReader.readLines();
//        System.out.println("总条数" + strings.size());
        List<List<String>> result = Lists.newArrayList();
        List<String> list = Lists.newArrayList();
        int k = 0;
        LineIterator lineIterator = FileUtils.lineIterator(new File("D://t_checkin_record-12.txt"), "utf-8");
        while (lineIterator.hasNext()) {

            if(k != 0 && k % 2000000 == 0) {
                BigExcelWriter writer= ExcelUtil.getBigWriter("d:/insert_" + k +".xlsx");
                writer.write(result);
                writer.close();
                result = new ArrayList<>();
            }
            String next = lineIterator.next();
//            System.out.println(next);
            Matcher m = r.matcher(next);
            if (m.find()) {
                String num = m.group(0).replace("=", "").replace("/", "").trim();
                list.add(num);
                k = k + 1;
                if (k != 0 && k % 13 == 0) {
//                    List<String> strings1 = ObjectUtil.cloneByStream(list);
                    result.add(list);
                    list = new ArrayList<>();
                }
            }
            System.out.println(k);
        }
        BigExcelWriter writer= ExcelUtil.getBigWriter("d:/insert_" + k +".xlsx");
        writer.write(result);
        writer.close();


    }
}


