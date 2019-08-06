package com.ex.study;


import jdk.management.resource.ResourceId;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheReplacements {
    public static void main(String[] args) {
//        // 生成 Pattern 对象并且编译一个简单的正则表达式"cat"
//        Pattern p = Pattern.compile("cat");
//        // 用 Pattern 类的 matcher() 方法生成一个 Matcher 对象
//        Matcher m = p.matcher("fatcatfatcatfat");
//        StringBuffer sb = new StringBuffer();
//        while(m.find()){
//            //此时sb为fatdogfatdog，cat被替换为dog,并且将最后匹配到之前的子串都添加到sb对象中
//            m.appendReplacement(sb,"dog");
//        }
//        //此时sb为fatdogfatdogfat，将最后匹配到后面的子串添加到sb对象中
//        m.appendTail(sb);
//        //输出内容为fatdogfatdogfat
//        System.out.println("sb:"+sb);
        String str ="fatcatfatcatfat";
        String s = str.replaceAll("cat", "dog");
        System.out.println(s);

    }
}
