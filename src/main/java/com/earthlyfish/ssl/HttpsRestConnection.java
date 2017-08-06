package com.earthlyfish.ssl;

import javax.net.ssl.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class HttpsRestConnection {

    public static void main(String[] args) throws Exception {
        //add https start..................
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustAllManager[] tm = { new TrustAllManager() };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        String path = "https://100.2.30.152/authentication";
        URL url = new URL(path);
        HttpsURLConnection connection = (HttpsURLConnection) url
                .openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setSSLSocketFactory(ssf);
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        connection.setHostnameVerifier(allHostsValid);
        // add https end..................

        String json = "{\"username\": \"admin\",\"password\": \"admin@inspur\",\"locale\": \"cn\",\"domain\": \"internal\",\"captcha\": \"\"}";

        OutputStream out = connection.getOutputStream();
        byte[] b = new byte[1024];
        out.write(json.getBytes());
        out.flush();
        out.close();

        int code = connection.getResponseCode();
        if (code == 200) {
            InputStream in = connection.getInputStream();
            while (-1 != in.read(b)) {
                System.out.println(new String(b));
            }
        }
    }
}
