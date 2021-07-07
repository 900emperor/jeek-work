package com.mu.week02;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtils {

    public static void main(String[] args) throws IOException {
        String urlTest = "http://localhost:8801";
        // 1.创建httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2. 创建HttpGet
        HttpGet httpGetTest1 = new HttpGet(urlTest);
        // 3. 请求执行，获取响应
        CloseableHttpResponse response =  httpclient.execute(httpGetTest1);
        System.out.println(response);
        // 4.获取响应实体
        HttpEntity entityTest = response.getEntity();
        System.out.println(EntityUtils.toString(entityTest,"utf-8"));
        response.close();
        httpclient.close();
    }

}
