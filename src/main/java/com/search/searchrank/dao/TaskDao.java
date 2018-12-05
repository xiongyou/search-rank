package com.search.searchrank.dao;

import com.search.searchrank.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> 任务DAO </p>
 *
 * @author 2018年12月03日 19:14
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */
@Repository
public interface TaskDao extends JpaRepository<Task,Long> {
    /**
     * 通过状态查询任务
     * @param status
     * @return
     */
    List<Task> findTaskByStatus(Integer status);

    /**
     * 通过网址查询任务
     * @param site
     * @return
     */
    List<Task> findTaskBySite(String site);

    /**
     * 通过网址与关键词列表查询任务
     * @param site
     * @param keywords
     * @return
     */
    List<Task> findTaskBySiteAndKeywordIn(String site,List<String> keywords);

    /**
     * 查询搜索引擎包含的任务
     * @param searchEngines
     * @return
     */
    List<Task> findTaskBySearchEngineIn(List<String > searchEngines);

    /**
     * 查询达到期望排名的任务
     */

}
