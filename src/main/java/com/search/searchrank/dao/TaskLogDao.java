package com.search.searchrank.dao;

import com.search.searchrank.domain.TaskLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p> 任务日志DAO </p>
 *
 * @author 2018年12月03日 19:15
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */
@Repository
public interface TaskLogDao extends JpaRepository<Long,TaskLog> {
}
