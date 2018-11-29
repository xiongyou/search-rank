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
    @Deprecated
    private Integer start;
    /**
     * 页面跨度，与start一起拼凑链接
     */
    @Deprecated
    private Integer setp;
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

}
