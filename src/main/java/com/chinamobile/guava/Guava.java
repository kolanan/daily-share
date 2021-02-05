package com.chinamobile.guava;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Guava {

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
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
                if(intersection.size() > 0) {
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

        String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIL7pbQ+5KKGYRhw7jE31hmA"
                + "f8Q60ybd+xZuRmuO5kOFBRqXGxKTQ9TfQI+aMW+0lw/kibKzaD/EKV91107xE384qOy6IcuBfaR5lv39OcoqNZ"
                + "5l+Dah5ABGnVkBP9fKOFhPgghBknTRo0/rZFGI6Q1UHXb+4atP++LNFlDymJcPAgMBAAECgYBammGb1alndta"
                + "xBmTtLLdveoBmp14p04D8mhkiC33iFKBcLUvvxGg2Vpuc+cbagyu/NZG+R/WDrlgEDUp6861M5BeFN0L9O4hz"
                + "GAEn8xyTE96f8sh4VlRmBOvVdwZqRO+ilkOM96+KL88A9RKdp8V2tna7TM6oI3LHDyf/JBoXaQJBAMcVN7fKlYP"
                + "Skzfh/yZzW2fmC0ZNg/qaW8Oa/wfDxlWjgnS0p/EKWZ8BxjR/d199L3i/KMaGdfpaWbYZLvYENqUCQQCobjsuCW"
                + "nlZhcWajjzpsSuy8/bICVEpUax1fUZ58Mq69CQXfaZemD9Ar4omzuEAAs2/uee3kt3AvCBaeq05NyjAkBme8SwB0iK"
                + "kLcaeGuJlq7CQIkjSrobIqUEf+CzVZPe+AorG+isS+Cw2w/2bHu+G0p5xSYvdH59P0+ZT0N+f9LFAkA6v3Ae56OrI"
                + "wfMhrJksfeKbIaMjNLS9b8JynIaXg9iCiyOHmgkMl5gAbPoH/ULXqSKwzBw5mJ2GW1gBlyaSfV3AkA/RJC+adIjsRGg"
                + "JOkiRjSmPpGv3FOhl9fsBPjupZBEIuoMWOC8GXK/73DHxwmfNmN7C9+sIi4RBcjEeQ5F5FHZ";

        String publicKey  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwvWkwV5rvACLA+HUAR13HND9+GgHYsS2RlAwT/+Wev8u93E8NE0OiHYzPDMGmwrEewCiRRs4emXarCLCCG9H/5TyxIrfVU6UIc7ZwQRDN/Bh9F1O5uTREvKh8jJPkz8NXoNnul36NV4ifkSAgGZScPNC6C4vAqUf1otc8yARwRQIDAQAB";


        RSA rsa = new RSA(PRIVATE_KEY, publicKey);
        String s  = rsa.encryptBase64("123456".getBytes(), KeyType.PublicKey);
        String str = rsa.decryptStr(s, KeyType.PrivateKey);
        System.out.println(str);
    }


}
