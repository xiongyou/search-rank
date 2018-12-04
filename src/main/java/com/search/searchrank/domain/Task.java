package com.search.searchrank.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> 任务表 </p>
 *
 * @author 2018年11月28日 18:59
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */
@Entity
@Table(name ="task_info")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
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
    @Column(name = "search_engine")
    private String searchEngine;
    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;
    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;
    /**
     * 优先级
     */
    private Integer privillage;
    /**
     * 期望排名
     */
    private Integer expectRank;
    /**
     * 当前排名
     */
    private Integer rank;
    /**
     * 状态，0表示初始，1表示执行中，2表示完成，-1表示失效
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getExpectRank() {
        return expectRank;
    }

    public void setExpectRank(Integer expectRank) {
        this.expectRank = expectRank;
    }
}
