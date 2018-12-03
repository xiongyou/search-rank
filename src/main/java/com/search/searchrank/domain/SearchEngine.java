package com.search.searchrank.domain;

/**
 * <p> 搜索引擎 </p>
 *
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */

public class SearchEngine {
    /**
     * 网址
     */
    private String url;
    /**
     * 页数开始
     */
    private Integer start;
    /**
     * 页面跨度，与start一起拼凑链接
     */
    private Integer step;
    /**
     * 解析链接的模式，正则？xpath
     */
    private String linkPattern;
    /**
     * 下一页按钮xpath
     */
    private String nextPage;
    /**
     * 搜索框xpath
     */
    private String searchBox;
    /**
     * 搜索按钮xpath
     */
    private String searchButton;
    /**
     * 停留时间，毫秒
     */
    private Integer stay;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Deprecated
    public Integer getStart() {
        return start;
    }
    @Deprecated
    public void setStart(Integer start) {
        this.start = start;
    }
    @Deprecated
    public Integer getStep() {
        return step;
    }
    @Deprecated
    public void setStep(Integer step) {
        this.step = step;
    }

    public String getLinkPattern() {
        return linkPattern;
    }

    public void setLinkPattern(String linkPattern) {
        this.linkPattern = linkPattern;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getSearchBox() {
        return searchBox;
    }

    public void setSearchBox(String searchBox) {
        this.searchBox = searchBox;
    }

    public String getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(String searchButton) {
        this.searchButton = searchButton;
    }

    public Integer getStay() {
        return stay;
    }

    public void setStay(Integer stay) {
        this.stay = stay;
    }
}
