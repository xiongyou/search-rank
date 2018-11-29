package com.search.searchrank.domain;

import java.util.Map;

/**
 * <p> 配置解析类 </p>
 *
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */

public class AppConfig {
    /**
     * 搜索引擎配置
     */
    private Map<String,SearchEngine> searchEngines;
    /**
     * 代理网站配置
     */
    private Map<String,ProxyConf> proxies;

    public Map<String, SearchEngine> getSearchEngines() {
        return searchEngines;
    }

    public void setSearchEngines(Map<String, SearchEngine> searchEngines) {
        this.searchEngines = searchEngines;
    }

    public Map<String, ProxyConf> getProxies() {
        return proxies;
    }

    public void setProxies(Map<String, ProxyConf> proxies) {
        this.proxies = proxies;
    }
}
