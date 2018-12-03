package com.search.searchrank.dao;

import com.search.searchrank.domain.Proxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface ProxyDao extends JpaRepository<Long,Proxy> {
}
