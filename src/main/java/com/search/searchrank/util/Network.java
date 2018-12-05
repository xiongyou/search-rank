package com.search.searchrank.util;

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
	 * 检查百度是否连通
	 * @return
	 * @throws Exception
	 */
	public static boolean checkBaidu()  throws Exception{
		return isConnect("www.baidu.com");
	}

	/**
	 * 检查搜狗是否连通
	 * @return
	 * @throws Exception
	 */
	public static boolean checkSogou()  throws Exception{
		return isConnect("www.sogou.com");
	}

	/**
	 * 检查360是否连通
	 * @return
	 * @throws Exception
	 */
	public static boolean check360()  throws Exception{
		return isConnect("www.so.com");
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