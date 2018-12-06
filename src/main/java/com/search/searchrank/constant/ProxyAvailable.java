package com.search.searchrank.constant;

/**
 * <p> 代理可用性的值 </p>
 *
 * @author  2018年12月06日 8:47
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */

public enum ProxyAvailable {
    /**
     * 可用
     */
    AVAILABLE(1),
    /**
     * 不可用
     */
    NOT_AVAILABLE(0);

    private Integer value;

    ProxyAvailable(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
