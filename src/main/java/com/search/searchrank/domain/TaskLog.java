package com.search.searchrank.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> 任务日志 </p>
 *
 * @author 2018年12月03日 18:31
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */
@Entity
@Table(name = "task_log")
public class TaskLog {
    /**
     * 日志编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联任务
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "task_id",referencedColumnName = "id")
    private Task task;

    /**
     * 执行时间
     */
    @Column(name = "execute_time")
    private Date executeTime;
    /**
     * 代理表的关联
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "proxy_id",referencedColumnName = "id")
    private Proxy proxy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
}
