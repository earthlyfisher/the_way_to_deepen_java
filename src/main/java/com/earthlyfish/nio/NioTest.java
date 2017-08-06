package com.earthlyfish.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class NioTest {

    /**
     * 测试1.7的新功能<br/>
     * try with resourses<br/>
     * try()里面的资源必须实现Closeable
     */
    public void testTWR() {
        try (InputStream in = new FileInputStream(""); BufferedInputStream bin = new BufferedInputStream(in);) {
            byte[] b = new byte[1024];
            while (bin.read(b) != -1) {
                System.out.println(new String(b));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试1.7的新功能<br/>
     * Paths&Files
     */
    public void testFileNio() {
        Path path = Paths.get("D:/test.txt");
        try {
            List<String> lst = Files.readAllLines(path, Charset.forName("UTF-8"));
            for (String str : lst) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
