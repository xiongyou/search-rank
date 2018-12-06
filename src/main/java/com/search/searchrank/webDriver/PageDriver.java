package com.search.searchrank.webDriver;

import com.search.searchrank.domain.Proxy;
import com.search.searchrank.util.CompressUtil;
import com.search.searchrank.util.MyPath;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于生成webdriver，配置代理
 */
public class PageDriver {
    /**
     * 获取浏览器的代理扩展文件
     *
     * @param ipProxy
     * @return
     */
    public static String getChromeProxyExtension(Proxy ipProxy) {

        String path = MyPath.getProjectPath() + "\\libs";

        // 创建一个定制Chrome代理扩展(zip文件)
        String secPath = path + "\\userData\\Default\\Secure Preferences";
        File secFile = new File(secPath);
        //删除代理的扩展文件
        if (secFile.exists()) {
            secFile.delete();
        }
        // 创建一个定制Chrome代理扩展(zip文件)
        String zipPath = path + "\\Chrome-extension\\proxy.zip";
        File zipFile = new File(zipPath);
        if (zipFile.exists()) {
            zipFile.delete();
        }
        CompressUtil.zip(path + "\\Chrome-proxy-helper\\manifest.json",
                path + "\\Chrome-extension\\proxy.zip", false, null);

        // 扩展文件不存在，创建

        // 替换模板中的代理参数
        String background1Path = path + "\\Chrome-proxy-helper\\background1.js";

        File file = new File(background1Path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(isr);
            String backgroundContent = "";
            while (true) {
                String line = br.readLine();
                // 到达文件末尾
                if (line == null) {
                    break;
                }
                backgroundContent += line + "\r\n";
            }
            br.close();
            backgroundContent = backgroundContent.replaceAll("%proxy_host", ipProxy.getIp())
                    .replaceAll("%proxy_port", Integer.toString(ipProxy.getPort())).replaceAll("%username", ipProxy.getUserName())
                    .replaceAll("%password", ipProxy.getPassword());
            String backgroundPath = path + "\\Chrome-proxy-helper\\background.js";

            File bgfile = new File(backgroundPath);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(bgfile), "utf-8");
            BufferedWriter writer = new BufferedWriter(osw);
            writer.write(backgroundContent);
            writer.close();

            CompressUtil.zip(backgroundPath, zipPath, false, null);

            return zipPath;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用代理生成webDriver
     * @param ipProxy
     * @return
     */
    public static WebDriver generateDriver(Proxy ipProxy) {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        if (ipProxy.getUserName().equals("")) {

            String path = MyPath.getProjectPath();
            ChromeOptions options = new ChromeOptions();
            List<String> chromeArgs = new ArrayList<String>();
            chromeArgs.add("user-data-dir=" + path + "\\libs\\userData");

            //chromeArgs.add("--incognito");// 隐身模式
            String host = ipProxy.getIp() + ":" + ipProxy.getPort();
            chromeArgs.add("proxy-server=http://"
                    + host);//代理–proxy-server chromeArgs.add("--disable-images");//禁止图像 //
            options.addArguments("user-data-dir=" + path + "\\libs\\userData"); //
            //加载用户配置文件 // options.addArguments("--incognito");//隐身模式

            //模拟百度蜘蛛
            chromeArgs.add("user-agent=Mozilla/5.0 (compatible; Baiduspider-render/2.0; +http://www.baidu.com/search/spider.html)");
            options.addArguments(chromeArgs);
            WebDriver driver = new ChromeDriver(options);

            driver.manage().window().maximize();

            return driver;


        } else {

            String path = MyPath.getProjectPath();
            String extPath = getChromeProxyExtension(ipProxy);
            if (extPath != null) {
                ChromeOptions options = new ChromeOptions();
                List<String> chromeArgs = new ArrayList<String>();
                chromeArgs.add("user-data-dir=" + path + "\\libs\\userData");
                //chromeArgs.add("--incognito");// 隐身模式
                options.addArguments(chromeArgs);

                options.addExtensions(new File(extPath));
                WebDriver driver = new ChromeDriver(options);

                driver.manage().window().maximize();

                return driver;
            } else {
                return null;
            }
        }

    }

    /**
     * 不用代理生成webDriver
     * @return
     */
    public static WebDriver generateDriver() {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        String path = MyPath.getProjectPath();
        ChromeOptions options = new ChromeOptions();
        List<String> chromeArgs = new ArrayList<String>();
        // 创建一个定制Chrome代理扩展(zip文件)
		/*
		String secPath = path + "\\libs\\userData\\Default\\Secure Preferences";
		File secFile=new File (secPath); 
		//删除代理的扩展文件
		if(secFile.exists()){
			secFile.delete();
		}
		chromeArgs.add("user-data-dir=" + path + "\\libs\\userData");// 加载用户配置文件
		//chromeArgs.add("--incognito");// 隐身模式
		 
		 */
        chromeArgs.add("--disable-images");// 禁止图像
        chromeArgs.add("user-agent=Mozilla/5.0 (compatible; Baiduspider-render/2.0; +http://www.baidu.com/search/spider.html)");
        options.addArguments(chromeArgs);

        WebDriver driver = null;

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        return driver;
    }


}
