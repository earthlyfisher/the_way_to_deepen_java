import sun.net.www.ParseUtil;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by inspur on 2017/6/29.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Properties properties = System.getProperties();
        Enumeration<Object> enumeration = properties.keys();
        while (enumeration.hasMoreElements()) {
            Object key = enumeration.nextElement();
            System.out.println(key + "=" + properties.get(key));
        }

       /* File file = new File("D://footer.md");
        System.out.println(file.getCanonicalFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(ParseUtil.fileToEncodedURL(file));*/
    }

}
