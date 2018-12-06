package com.search.searchrank.constant;

/**
 * <p> 任务状态枚举类 </p>
 *
 * @author  2018年12月06日 9:04
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */

public enum TaskStatus {
    EXECUTE(1),
    FINISH(2);

    private Integer value;
    TaskStatus (Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
