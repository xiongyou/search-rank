package com.search.searchrank.util;

import com.search.searchrank.domain.ConfEntity;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

/**
 * <p> 获取配置信息，放入缓存 </p>
 *
 * @author xiongyou 2018年12月03日 18:54
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */

public class ConfCache {
    /**
     * 初始化配置
     */
    public ConfEntity initConf() {
        ConfEntity confEntity = new ConfEntity();
        try {
            Yaml yaml = new Yaml();            // 从文件中读
            String path = this.getClass().getClassLoader().getResource("appConfig.yml").getPath();
            confEntity = yaml.loadAs(new FileInputStream(new File(path)), ConfEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return confEntity;
    }

    public static void main(String[] args) {
        ConfCache confCache = new ConfCache();
        confCache.initConf();
    }
}
