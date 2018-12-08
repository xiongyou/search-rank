package com.search.searchrank.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> 代理ip地址实体类 </p>
 *
 * @author 2018年11月28日 18:58
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} ${date}
 * @modify by reason: {方法名}:{原因}
 */
@Entity
@Table(name="ip_proxy")
public class Proxy {
    /**
     * IP编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long ipProxyId;

    @Column(name="ip")
    private String ip; //IP地址

    @Column(name="port")
    private Integer port; //端口号

    @Column(name="add_time")
    private Date addTime; //添加代理到数据库的时间

    @Column(name="update_time")
    private Date updateTime;//代理更新时间

    @Column(name="used_time")
    private Date usedTime; //记录上一次被使用的时间

    @Column(name="in_use")
    private Integer using=0; //是否正在使用中

    @Column(name="available_baidu")
    private Integer availableBaidu;//百度是否可用

    @Column(name="available_360")
    private Integer available360;//360是否可用

    @Column(name="available_sogou")
    private Integer availableSogou;//搜狗是否可用

    @Column(name="times")
    private Integer times;//已经使用的次数

    @Column(name = "user_name")
    private String userName;

    private String password;
    /*
	@Column(name="types")
	private int types; //类型
	@Column(name="protocol")
	private int protocal;//协议
	@Column(name="country")
	private String country;//国家
	@Column(name="area")
	private String area;//区域
	@Column(name="update_time")
	private Date updateTime;//更新时间
	@Column(name="speed")
	private double speed;//响应速度
    */

    public Long getIpProxyId() {
        return ipProxyId;
    }

    public void setIpProxyId(Long ipProxyId) {
        this.ipProxyId = ipProxyId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Integer getUsing() {
        return using;
    }

    public void setUsing(Integer using) {
        this.using = using;
    }

    public Integer getAvailableBaidu() {
        return availableBaidu;
    }

    public void setAvailableBaidu(Integer availableBaidu) {
        this.availableBaidu = availableBaidu;
    }

    public Integer getAvailable360() {
        return available360;
    }

    public void setAvailable360(Integer available360) {
        this.available360 = available360;
    }

    public Integer getAvailableSogou() {
        return availableSogou;
    }

    public void setAvailableSogou(Integer availableSogou) {
        this.availableSogou = availableSogou;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String toString(){
        return "{\"ip\":"+"\""+this.ip+"\","+"\"port\":"+"\""+this.port+"\"}";
    }
}
