package com.search.searchrank.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> </p>
 *
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiJsonObject {

    ApiJsonProperty[] value(); //对象属性值

    String name();  //对象名称

}