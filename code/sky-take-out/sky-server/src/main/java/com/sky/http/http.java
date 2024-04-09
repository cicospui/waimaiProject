package com.sky.http;

import com.alibaba.fastjson.JSON;
import com.sky.entity.Orders;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @Description: 上传订单信息到区块链的方法
 * @Author: 徐晓宇
 * @Date: 2024/3/21 16:44
 */
public class http {
    public static String postToChain(Orders data) throws IOException {
        // 将 orders 转换为 JSON 字符串
        String postData = JSON.toJSONString(data);

        // 目标 URL
        String targetUrl = "http://localhost:8081/uploadOrder";

        // 创建 URL 对象
        URL url = new URL(targetUrl);

        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方法为 POST
        connection.setRequestMethod("POST");

        // 设置请求头部信息
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Content-Length", String.valueOf(postData.length()));

        // 开启输出流，并写入数据
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(postData.getBytes());
        outputStream.flush();
        outputStream.close();

        // 获取响应数据
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // 处理响应数据（String 类型）
        String responseData = response.toString();
        // 关闭连接
        connection.disconnect();
        return responseData;
    }
}
