package com.ddup.pattern.decoration;

import com.ddup.pattern.decoration.impl.BufferedStream;
import com.ddup.pattern.decoration.impl.CryptoStream;
import com.ddup.pattern.decoration.impl.FileStream;

public class MainTest {
    public static void main(String[] args) {
        FileStream fs = new FileStream();
        // 缓冲文件流
        BufferedStream bs = new BufferedStream(fs);
        // 加密缓冲文件流
        CryptoStream cs = new CryptoStream(bs);

        cs.read(1);
    }
}
