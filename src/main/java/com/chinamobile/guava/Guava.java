package com.chinamobile.guava;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Guava {

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = i + 1; j < accounts.size(); j++) {
                List<String> str1 = removeFirstBean(accounts.get(i));
                List<String> str2 = removeFirstBean(accounts.get(j));
                List<String> intersection = str1.stream().filter(str2::contains).collect(Collectors.toList());
                if (intersection.size() > 0) {
//                    List<String> union = str1.stream().filter(item -> str2.contains(item)).collect(Collectors.toList());
                    List<String> resultList = new ArrayList<>();
                    resultList.add(accounts.get(i).get(0));
                    resultList.addAll(intersection);
                    result.add(resultList);
                } else {
                    List<String> resultList = new ArrayList<>();
                    resultList.add(accounts.get(i).get(0));
                    resultList.addAll(str1);
                    result.add(resultList);
                }
            }

        }
        return result;
    }

    public static List<String> removeFirstBean(List<String> str) {
        ArrayList<String> objects = new ArrayList();
        for (int i = 1; i < str.size(); i++) {
            objects.add(str.get(i));
        }
        return objects;
    }

    public static void main(String[] args) {

        String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALC9aTBXmu8AIsD4dQBHXcc0P34aAdixLZGUDBP/5Z6/y73cTw0TQ6IdjM8MwabCsR7AKJFGzh6ZdqsIsIIb0f/lPLEit9VTpQhztnBBEM38GH0XU7m5NES8qHyMk+TPw1eg2e6Xfo1XiJ+RICAZlJw80LoLi8CpR/Wi1zzIBHBFAgMBAAECgYBwoFM+1iTKNvjupaXRhqjw/4+SaTpR/x49ze0gdYq67hgEpjbN6z/HODN54fQps5mxMdzoLwm8oCjp9wCb+OSJriXlTxrPmWLEOQGIIejzcYJFL8r7ZD2524fdrCT3kh/sh1OPAsYDDjzutO7VHiiuHeGAwNrbOC9prkXcv87dIQJBAPfCYfEVDTwnS77VhNUjRhz1QW4ltHwbDztls34aSvNvn9CZBXB7Tarxs3h7J6p0odM4UYWPKF0B6oe6i0X1i/0CQQC2nlArJMXGAuEgEPbgIvl8NDbsD7BerUyFVsLgq0RBzaR1yGu4yTZqpquMzSUgcAN+UpeADJ4p95tfkqMkSVPpAkEA8QLeU66u2T1Aqbr7JHrT0YeixotXLwMDpjwghyL8liKXzEuOxwSrYQcOFr12sJIUeWvnoYzJLvCWPMJyiPN74QJAEAZudIrCjDC+fqjecSbAi+StGSP7TyOX/m9xpHODBt872lYfFdUaYFuF3FI4OtN5iwFGaCczTe17hfSBOnLw2QJALOm4PySrLfMjC0b5Y+Y7jTRj5KENGkSCmpvrw4KROrFS6x0p1mYU4FKMxjyfb9LohblTLhJot4yw1uGW5QKQeA==";

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwvWkwV5rvACLA+HUAR13HND9+GgHYsS2RlAwT/+Wev8u93E8NE0OiHYzPDMGmwrEewCiRRs4emXarCLCCG9H/5TyxIrfVU6UIc7ZwQRDN/Bh9F1O5uTREvKh8jJPkz8NXoNnul36NV4ifkSAgGZScPNC6C4vAqUf1otc8yARwRQIDAQAB";


        RSA rsa = new RSA(null, publicKey);
        String s = rsa.encryptBase64("123456a.".getBytes(), KeyType.PublicKey);
//        try {
//            System.out.println(rsa.decryptStr("TvY69DCAF5NYDEWUGxUW5RW9SNpVW2l+8ekxOuTOCdkEAErzTTX+fc/L1PxvFqZ6aqieg+bwfEHJYkWGcQ5uahwWqQMs3fdwFOG91uF5mC34VwiiaP5MDqs028+JwVpaPh3PPMddTIrOuW7PyRmTWORU3L3LmQqa4bQWFi/lQp8=", KeyType.PrivateKey));
//        } catch (CryptoException e) {
//            System.out.println(e.getMessage());
//        }
        System.out.println(s);

        System.out.println(DateUtil.today());
    }


}
