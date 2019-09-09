package com.lu.ming.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 17:04 2019/8/28
 * Modified By:
 */
public class HttpClientUtils {

    public static final String GET = "get";

    public static final String POST = "post";

    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";

    public static final String REQUEST_HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/68.0";

    /**
     * get请求
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url){
        return createRequest(url,GET,null);
    }

    /**
     * 方法重载
     * 带cookie的get请求
     * @param url
     * @param cookie cookie
     * @return
     */
    public static String doGet(String url,String cookie){
        return createRequest(url,GET,cookie);
    }

    /**
     * post请求
     * @param url 请求地址
     * @param params 请求参数
     *               BasicNameValuePair... params是个数组
     * @return
     */
    public static String doPost(String url,BasicNameValuePair... params){
        return createRequest(url,POST,null,params);
    }

    /**
     方法重载
     * 带cookie的post请求
     * @param url
     * @param cookie
     * @param params
     * @return
     */
    public static String doPost(String url,String cookie, BasicNameValuePair... params){
        return createRequest(url,POST,cookie,params);
    }



    /**
     *封装成私有方法用来上面的方法重载
     * @param url 请求地址
     * @param requestMethod 请求方式
     * @param cookie cookie
     * @param params 请求参数 仅限于post
     * @return
     */
    private static String createRequest(String url, String requestMethod,String cookie, BasicNameValuePair... params){
        //创建HttpClient客户端 ：打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //返回结果
        String result = null;

        try {
            //请求方式
            HttpGet httpGet = null;
            HttpPost httpPost = null;

            //响应
            CloseableHttpResponse httpResponse = null;



            //get请求
            if (GET.equals(requestMethod)){
                httpGet = new HttpGet(url);
                httpGet.setHeader("Cookie",cookie);
                httpGet.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("User-Agent",REQUEST_HEADER_USER_AGENT);

                //发送请求
                httpResponse = httpClient.execute(httpGet);
            }
            //post请求
            else if (POST.equals(requestMethod)){
                httpPost = new HttpPost(url);
                httpPost.setHeader("Cookie",cookie);
                httpPost.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("User-Agent",REQUEST_HEADER_USER_AGENT);

                //如果有参数传进来
                if (params != null && params.length > 0){
                    //把数组里的参数转换成集合
                    List<BasicNameValuePair> parameters = Arrays.asList(params);
                    httpPost.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));
                }

                //发送请求
                httpResponse = httpClient.execute(httpPost);
            }

            //调用 HttpResponse 的 getEntity() 方法可获取 HttpEntity 对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}

