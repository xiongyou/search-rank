package com.search.searchrank.param;

import java.util.Date;

/**
 * <p> 提交任务的参数 </p>
 *
 * @author 2018年12月03日 19:38
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */

public class TaskParam {

    /**
     * 站点
     */
    private String site;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 搜索引擎
     */
    private String searchEngine;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 优先级
     */
    private Integer privillage;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPrivillage() {
        return privillage;
    }

    public void setPrivillage(Integer privillage) {
        this.privillage = privillage;
    }
}
