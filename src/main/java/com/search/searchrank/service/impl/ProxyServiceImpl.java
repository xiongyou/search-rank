package com.search.searchrank.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.search.searchrank.dao.ProxyDao;
import com.search.searchrank.domain.ConfEntity;
import com.search.searchrank.domain.Proxy;
import com.search.searchrank.domain.ProxyConf;
import com.search.searchrank.service.ProxyService;
import com.search.searchrank.util.ConfCache;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("proxyService")
@Transactional
public class ProxyServiceImpl implements ProxyService {

    @Autowired
    ProxyDao proxyDao;
    ConfCache confCache = new ConfCache();


    private String proxyUrl = "";// 代理地址
    private String proxyReg = "";// ip与端口的正则表达式
    private String encode = "UTF8";// 页面编码
    private int ipInterval = 30000;// 再次重新获取的时间
    private long getProxyTime = System.currentTimeMillis();

    public void init() {
        ConfEntity confEntity = confCache.initConf();
        Set<Map.Entry<String,ProxyConf>> proxySet = confEntity.getProxies().entrySet();
        Iterator<Map.Entry<String,ProxyConf>> iterator = proxySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,ProxyConf> proxyConfEntry =  iterator.next();
            ProxyConf proxyConf = proxyConfEntry.getValue();
            proxyUrl = proxyConf.getServer();// 代理地址
            proxyReg = proxyConf.getRegex();// ip与端口的正则表达式
            encode = proxyConf.getCharset();// 页面编码
            ipInterval = proxyConf.getInterval();// 再次重新获取的时间
        }

    }

    public synchronized String getIp() {
        // TODO Auto-generated method stub
        // 1、判断是否还有可用ip，新IP与上次使用间隔较长的ip
        // 如果上次去取得代理IP列表的时间超过30分钟，则删除原有的ip列表，重新获取添加到数据库
        if (this.getProxyTime + ipInterval * 60 * 1000 < System.currentTimeMillis()) {
            // 删除
            //commonDao.executeJPQL("delete from IPProxy");
            // 重新获取并保存
            this.saveIp(this.ipList(this.getOneHtml(), proxyReg));
            this.getProxyTime = System.currentTimeMillis();//重新为获取代理的时间赋值
        }

        // 判断ip表是否为空，如果为空，则需要获取IP
        Proxy ipProxy = null;
        //IPProxy ipProxy = new IPProxy();
        int errorCount = 0;
        JSONObject json = new JSONObject();
        while (ipProxy == null) {
            if (errorCount == 5) {
                json.put("msg", "代理IP获取失败：连续5次出错！");
                json.put("success", false);

                return json.toString();
            }

            List<Proxy> ipProxyList = proxyDao.findProxiesByUsingOrderByAddTime(0);
            if (ipProxyList.size() == 0) {
                this.saveIp(this.ipList(this.getOneHtml(), proxyReg));
            } else {
                ipProxy = ipProxyList.get(0);
                ipProxy.setUsedTime(new Date());
                ipProxy.setTimes(ipProxy.getTimes() + 1);
                ipProxy.setUsing(1);
                proxyDao.save(ipProxy);
                break;
            }
            errorCount++;
        }

        try {
            json.put("ip", ipProxy.getIp());
            json.put("port", ipProxy.getPort());
            //json.put("ip", "http-dyn.abuyun.com");
            //json.put("port", 9020);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put("msg", "代理IP获取失败，" + e.toString());
            json.put("success", false);
        }
        return json.toString();

    }

    /**
     * 获取网页内容by URL
     *
     * @param
     * @return 网页内容
     * @throws IOException
     */
    public String getOneHtml() {
        StringBuffer content = new StringBuffer();
        HttpURLConnection connection = null;
        try {
            URL u = new URL(proxyUrl);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream in = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, encode);
                BufferedReader reader = new BufferedReader(isr);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                    content.append("\n");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return content.toString();
    }

    public void saveIp(Map<String, Integer> proxyIpMap) {
        for (String proxyHost : proxyIpMap.keySet()) {
            Integer proxyPort = proxyIpMap.get(proxyHost);

            Proxy ipProxy = new Proxy();
            ipProxy.setIp(proxyHost);
            ipProxy.setPort(proxyPort);
            ipProxy.setAddTime(new Date());
            ipProxy.setTimes(0);
            ipProxy.setUsing(0);
            ipProxy.setAvailable(1);
            proxyDao.save(ipProxy);
        }
    }

    /**
     * 批量代理IP有效检测 ， 可以先直接批量获取，不验证，让客户端自己去验证
     *
     * @param proxyIpMap
     * @param reqUrl
     */
    public void checkProxyIp(Map<String, Integer> proxyIpMap, String reqUrl) {

        for (String proxyHost : proxyIpMap.keySet()) {
            Integer proxyPort = proxyIpMap.get(proxyHost);

            int statusCode = 0;
            try {
                HttpClient httpClient = new HttpClient();
                httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);

                // 连接超时时间（默认6秒 6000ms） 单位毫秒（ms）
                int connectionTimeout = 6000;
                // 读取数据超时时间（默认10秒 10000ms） 单位毫秒（ms）
                int soTimeout = 10000;
                httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
                httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

                HttpMethod method = new GetMethod(reqUrl);

                statusCode = httpClient.executeMethod(method);
                // 如果成功，则将ip保存到数据库
                if (statusCode == 200) {
                    Proxy ipProxy = new Proxy();
                    ipProxy.setIp(proxyHost);
                    ipProxy.setPort(proxyPort);
                    ipProxy.setAddTime(new Date());
                    ipProxy.setTimes(0);
                    ipProxy.setUsing(0);
                    ipProxy.setAvailable(1);
                    proxyDao.save(ipProxy);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.format("%s:%s-->%s\n", proxyHost, proxyPort, statusCode);
        }
    }

    /**
     * 代理IP有效检测
     *
     * @param proxyIp
     * @param proxyPort
     * @param reqUrl
     */
    public void checkProxyIp(String proxyIp, int proxyPort, String reqUrl) {
        Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
        proxyIpMap.put(proxyIp, proxyPort);
        checkProxyIp(proxyIpMap, reqUrl);
    }

    /**
     * 将ip代理页面的内容进行解析，得到代理的Map
     *
     * @param content
     * @param regex
     * @return
     */
    public Map<String, Integer> ipList(String content, String regex) {

        Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(content);

        while (ma.find()) {
            proxyIpMap.put(ma.group(1), Integer.parseInt(ma.group(2)));
        }
        return proxyIpMap;
    }
}
