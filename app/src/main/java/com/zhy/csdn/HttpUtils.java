package com.zhy.csdn;

import com.zhy.bean.NetworkException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static String doGet(String srcUrl)
            throws NetworkException {
        StringBuffer buf = new StringBuffer();
        try {
            URL url = new URL(srcUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            if (conn.getResponseCode() == 200) {
                InputStream in = conn.getInputStream();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(in));
                String lineStr;
                while ((lineStr = br.readLine()) != null) {
                    buf.append(lineStr);
                }
                br.close();
                in.close();
            } else {
                throw new NetworkException("法访问网络出错啦!");
            }
        } catch (Exception e) {
            throw new NetworkException("无法访问网络!");
        }
        return buf.toString();
    }
}
