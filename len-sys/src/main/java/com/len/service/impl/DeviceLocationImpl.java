package com.len.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.len.service.DeviceLocation;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DeviceLocationImpl implements DeviceLocation {

    @Override
    public String getAddress(String content, String encodingString) {
        // 这里调用淘宝API
        // String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        String pointaddress="";
        String urlStr="https://api.map.baidu.com/location/ip?v=2.0&ak=S9GPZ6RA1MXNlNfr76ecnTmd7pAWuZMN&coor=bd09ll";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息

            String returnStr = getResult(urlStr, content, encodingString);
          //  System.out.println("直接返回的值："+ returnStr);
            if (returnStr != null) {
                // 处理返回的省市区信息
                //  System.out.println("(1) unicode转换成中文前的returnStr : " + returnStr);
                returnStr = decodeUnicode(returnStr);
                System.out.println("(2) unicode转换成中文后的returnStr : " + returnStr);
                String[] temp = returnStr.split(",");
                if (temp.length < 3) {
                    return null;//无效IP，局域网测试
                }
           //     List<String> list = Arrays.asList(returnStr.split(","));
         /*       for (String string : list) {
                    List<String> list1;
                    if (string.contains("content")) {
                        list1 = Arrays.asList(string.split(","));
                        for (String string1 : list1) {
                            if (string1.contains("address")) {
                                List<String> list2 = Arrays.asList(string1.split(":"));
                                for (String string2 : list2) {
                                    //    System.out.println("数据解析："+string2);
                                }
                            }
                        }
                    }
                }*/
                JSONObject jsonObject = JSON.parseObject(returnStr);
                JSONObject jsonObject1 = jsonObject.getJSONObject("content");

                String jsonObject2 = jsonObject1.getString("address");

                JSONObject jsonObject3 = jsonObject1.getJSONObject("point");
         /*       String location = jsonObject1.getString("address");
                String string3 = jsonObject1.getString("point");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("point");
                String string4 = jsonObject2.getString("x");
                String string5 = jsonObject2.getString("y");
                JSONObject point = JSON.parseObject(string3);*/

          /*      System.out.println("城市:" + location);
                System.out.println("经纬度：" + string3);*/
           //     System.out.println(jsonObject2);
                System.out.println(jsonObject3);

              String SjsonObject3 = jsonObject3.toString();
              StringBuilder stringBuilder = new StringBuilder(SjsonObject3);
                int index1= stringBuilder.indexOf("}");
                stringBuilder.insert(index1,",\"addressname\":\"");
               // String a = ""+stringBuilder;
                int index3= stringBuilder.indexOf("}");
                stringBuilder.insert(index3,jsonObject2);
          //      a= a+jsonObject2;
        //        StringBuilder b = new StringBuilder(a);
                int index2= stringBuilder.indexOf("}");
                stringBuilder.insert(index2,"\"");
                String c =""+stringBuilder;
                pointaddress= c;
    //           System.out.println(pointaddress);
            }

           return pointaddress;
    }

    /**
     * @param urlStr
     *            请求的地址
     * @param content
     *            请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding
     *            服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    /**
     * unicode 转换成 中文
     *
     * @author fanhui
     * @param theString
     * @return
     */
    public String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
}
