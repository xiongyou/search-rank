package com.search.searchrank.webDriver;

import com.search.searchrank.dao.TaskDao;
import com.search.searchrank.dao.TaskLogDao;
import com.search.searchrank.domain.SearchEngine;
import com.search.searchrank.domain.Task;
import com.search.searchrank.domain.TaskLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.text.ParagraphView;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p> 执行搜索 </p>
 *
 * @author 2018年12月05日 19:44
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */

public class SearchExecutor {
    private TaskDao taskDao;
    private TaskLogDao taskLogDao;
    //TODO 按task的引擎取对应的实例配置
    private SearchEngine searchEngine = new SearchEngine();

    /**
     * 执行排名任务，在此之前，需要设置webDriver的代理
     *
     * @param driver
     */
    public void execute(WebDriver driver, Task task) {
        TaskLog taskLog = new TaskLog();
        task.setRank(1);
        //1.打开指定搜索网站
        String curWindowHandle = driver.getWindowHandle();
        driver.get(task.getSearchEngine());
        try {
            //2.传入关键到搜索框
            WebElement searchBoxEle = driver.findElement(By.cssSelector(searchEngine.getSearchBox()));
            searchBoxEle.clear();
            searchBoxEle.sendKeys(task.getKeyword());
            Thread.sleep(2000);
            //3.点击搜索
            driver.findElement(By.cssSelector(searchEngine.getSearchButton())).click();
            Thread.sleep(2000);

            while (true) {
                //4.获取所有的链接元素 a[id*='id_pattern']
                WebElement targetUrlEle = driver.findElement(By.cssSelector("a[href*='" + task.getSite() + "']"));

                //5.进行与指定的排名网址匹配（域名匹配）
                //6.未匹配到点击下一页
                if (null == targetUrlEle) {
                    task.setRank(task.getRank() + 1);
                    //获取下一页点击
                    driver.findElement(By.cssSelector(searchEngine.getNextPage())).click();
                    Thread.sleep(2000);
                } else {
                    //7.存储排名网址所在搜索网站的页码
                    taskDao.save(task);
                    //8.打开排名网址
                    targetUrlEle.click();
                    pocessTargeSite(driver,curWindowHandle);
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        //TODO 此处日志保存可以放在外面调用该execute方法的地方，因为还有proxy
        taskLog.setExecuteTime(new Date());
        taskLog.setTask(task);
        taskLogDao.save(taskLog);
    }

    /**
     * 处理目标网站
     * @param driver
     * @param curWindowHandle
     */
    private void pocessTargeSite(WebDriver driver,String curWindowHandle){
        //get all windows
        Set<String> handles = driver.getWindowHandles();
        for (String s : handles) {
            //current page is don't close
            if (s.equals(curWindowHandle))
                continue;
            else {
                WebDriver window = driver.switchTo().window(s);
                //9.点击排名网址页面中的三个页面
                List<WebElement> links = driver.findElements(By.cssSelector("a"));
                int times = 0;
                for (WebElement link : links) {
                    WebDriver window1;
                    System.out.println(link.getText());

                    link.click();
                    String currentWindow = driver.getWindowHandle();
                    //get all windows
                    Set<String> handles1 = driver.getWindowHandles();
                    for (String s1 : handles1) {
                        //current page is don't close
                        //不能关闭搜索页面与网站首页
                        if (s1.equals(currentWindow)||s1.equals(curWindowHandle))
                            continue;
                        else {
                            //关闭排名网站打开的窗口
                            window1 = driver.switchTo().window(s);
                            window1.manage().window().maximize();
                            window1.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                            window1.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                            //close the table window
                            window1.close();
                        }
                        //swich to current window
                        //切换到排名网站窗口
                        driver.switchTo().window(currentWindow);
                    }
                    //如果到达3次则退出
                    if (times == 3) {
                        break;
                    } else {
                        times++;
                    }
                }
                //关闭排名网站
                window.close();
            }
            //切换搜索窗口
            driver.switchTo().window(curWindowHandle);
        }
    }

}
