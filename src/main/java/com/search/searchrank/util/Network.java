package com.search.searchrank.util;

import com.search.searchrank.constant.ProxyAvailable;
import com.search.searchrank.constant.SearchEngineConstant;
import com.search.searchrank.dao.ProxyDao;
import com.search.searchrank.domain.Proxy;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/** 
 *  
 * 
 *  */
@SuppressWarnings("unused")
public class Network {

	/**
	 * 静态类，通过构造函数自动注入
	 */
	private static ProxyDao proxyDao;
	@Autowired
	public Network(ProxyDao proxyDao){
		Network.proxyDao = proxyDao;
	}
	public Network(){

	}

	/**
	 * 对单个代理所有网站进行验证
	 * @param proxy
	 */
	public static Proxy updateProxy (Proxy proxy){
		proxy = check360(checkSogou(checkBaidu(proxy)));
		return proxy;
	}

	/**
	 * 代理IP有效检测 ， 可以先直接批量获取，不验证，让客户端自己去验证
	 *
	 * @param
	 * @param reqUrl
	 */
	public static boolean checkProxyIp(Proxy proxy, String reqUrl) {
		return checkProxyIp(proxy.getIp(),proxy.getPort(),proxy.getUserName(),proxy.getPassword(),reqUrl);
	}

	/**
	 * 代理IP有效检测 ， 可以先直接批量获取，不验证，让客户端自己去验证
	 *
	 * @param
	 * @param reqUrl
	 */
	public static boolean checkProxyIp(String proxyHost, Integer proxyPort, String userName, String password, String reqUrl) {

		int statusCode = 0;
		try {
			HttpClient httpClient = new HttpClient();
			httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);
			httpClient.getParams().setAuthenticationPreemptive(true);
			// 如果代理需要密码验证，这里设置用户名密码
			if (userName != null && !userName.equals("")) {
				httpClient.getState().setProxyCredentials(AuthScope.ANY,
						new UsernamePasswordCredentials(userName, password));
			}
			// 连接超时时间（默认5秒 5000ms） 单位毫秒（ms）
			int connectionTimeout = 5000;
			// 读取数据超时时间（默认10秒 10000ms） 单位毫秒（ms）
			int soTimeout = 10000;
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

			HttpMethod method = new GetMethod(reqUrl);

			statusCode = httpClient.executeMethod(method);
			// 如果成功，则将ip保存到数据库
			if (statusCode == 200) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.format("代理%s:%s不可用-->%s\n", proxyHost, proxyPort, statusCode);
			return false;
		} finally {
			System.out.format("%s:%s-->%s\n", proxyHost, proxyPort, statusCode);
		}

	}

	/**
	 * 检查百度是否连通
	 * @return
	 * @throws Exception
	 */
	public static Proxy checkBaidu(Proxy proxy)  {
		if(checkProxyIp(proxy, SearchEngineConstant.HOME_BAIDU)){
			proxy.setAvailableBaidu(ProxyAvailable.AVAILABLE.getValue());
		}
		else {
			proxy.setAvailableBaidu(ProxyAvailable.NOT_AVAILABLE.getValue());
		}
		return proxy;
	}

	/**
	 * 检查搜狗是否连通
	 * @return
	 * @throws Exception
	 */
	public static Proxy checkSogou(Proxy proxy) {
		if(checkProxyIp(proxy,SearchEngineConstant.HOME_SOGOU)){
			proxy.setAvailableSogou(ProxyAvailable.AVAILABLE.getValue());
		}
		else {
			proxy.setAvailableSogou(ProxyAvailable.NOT_AVAILABLE.getValue());
		}
		return proxy;
	}

	/**
	 * 检查360是否连通
	 * @return
	 * @throws Exception
	 */
	public static Proxy check360(Proxy proxy){
		if(checkProxyIp(proxy,SearchEngineConstant.HOME_360)){
			proxy.setAvailable360(ProxyAvailable.AVAILABLE.getValue());
		}
		else {
			proxy.setAvailable360(ProxyAvailable.NOT_AVAILABLE.getValue());
		}
		return proxy;
	}

	// 判断网络状态
	public static boolean isConnect(String website) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		
			Process process = runtime.exec("ping " + website + " -n 1");
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
				// System.out.println("返回值为:"+line);
			}
			is.close();
			isr.close();
			br.close();

			if (null != sb && !sb.toString().equals("")) {
				
				if (sb.toString().indexOf("TTL") > 0) {
					return true;
				} else {
					// 网络不畅通
					System.out.println("网络连接失败，请检查网络连接！");
					return false;
				}
			}
			return false;
		
	}
}