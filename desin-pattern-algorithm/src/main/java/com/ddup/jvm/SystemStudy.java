package com.ddup.jvm;

import java.util.Properties;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/9/16 13:50
 */
public class SystemStudy {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        System.out.println(System.getProperty("ZookeeperPath"));
        for (Object key : properties.keySet()) {
            System.out.println();
            System.out.println(key + "-->" + properties.get(key));
        }
    }
}
