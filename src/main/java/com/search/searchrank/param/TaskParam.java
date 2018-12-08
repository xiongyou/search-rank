package com.search.searchrank.param;

import java.util.Date;
import java.util.List;

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
     * 关键词列表，逗号分隔
     */
    private List<String> keywords;
    /**
     * 搜索引擎列表，逗号分隔
     */
    private List<String> searchEngines;
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
    private Integer priority;

    /**
     * 期望排名
     */
    private Integer expectRank;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getSearchEngines() {
        return searchEngines;
    }

    public void setSearchEngines(List<String> searchEngines) {
        this.searchEngines = searchEngines;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getExpectRank() {
        return expectRank;
    }

    public void setExpectRank(Integer expectRank) {
        this.expectRank = expectRank;
    }
}
