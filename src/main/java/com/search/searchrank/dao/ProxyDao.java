package com.search.searchrank.dao;

import com.search.searchrank.domain.Proxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> 代理DAO </p>
 *
 * @author 2018年12月03日 19:14
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */
@Repository
public interface ProxyDao extends JpaRepository<Proxy,Long> {
    /**
     * 通过可用状态查询代理ip
     * @param using
     * @return
     */
    List<Proxy> findProxiesByUsingOrderByAddTime(Integer using);

    /**
     * 获取360可用的代理
     * @param available
     * @return
     */
    List<Proxy> findProxiesByAvailable360(Integer available);

    /**
     * 获取百度可用的代理
     * @param available
     * @return
     */
    List<Proxy> findProxiesByAvailableBaidu(Integer available);
    /**
     * 获取搜狗可用的代理
     * @param available
     * @return
     */
    List<Proxy> findProxiesByAvailableSogou(Integer available);

    /**
     * 通过ip与端口判断是否存在
     * @param ip
     * @param port
     * @return
     */
    Proxy findProxyByIpAndPort(String ip,Integer port);
}
