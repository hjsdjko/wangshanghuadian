package com.entity.model;

import com.entity.XianhuaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 鲜花
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XianhuaModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 鲜花名称
     */
    private String xianhuaName;


    /**
     * 鲜花编号
     */
    private String xianhuaUuidNumber;


    /**
     * 鲜花照片
     */
    private String xianhuaPhoto;


    /**
     * 花语
     */
    private String xianhuaHuayu;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 鲜花类型
     */
    private Integer xianhuaTypes;


    /**
     * 鲜花数量
     */
    private Integer xianhuaKucunNumber;


    /**
     * 鲜花原价
     */
    private Double xianhuaOldMoney;


    /**
     * 现价
     */
    private Double xianhuaNewMoney;


    /**
     * 鲜花热度
     */
    private Integer xianhuaClicknum;


    /**
     * 鲜花介绍
     */
    private String xianhuaContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer xianhuaDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：鲜花名称
	 */
    public String getXianhuaName() {
        return xianhuaName;
    }


    /**
	 * 设置：鲜花名称
	 */
    public void setXianhuaName(String xianhuaName) {
        this.xianhuaName = xianhuaName;
    }
    /**
	 * 获取：鲜花编号
	 */
    public String getXianhuaUuidNumber() {
        return xianhuaUuidNumber;
    }


    /**
	 * 设置：鲜花编号
	 */
    public void setXianhuaUuidNumber(String xianhuaUuidNumber) {
        this.xianhuaUuidNumber = xianhuaUuidNumber;
    }
    /**
	 * 获取：鲜花照片
	 */
    public String getXianhuaPhoto() {
        return xianhuaPhoto;
    }


    /**
	 * 设置：鲜花照片
	 */
    public void setXianhuaPhoto(String xianhuaPhoto) {
        this.xianhuaPhoto = xianhuaPhoto;
    }
    /**
	 * 获取：花语
	 */
    public String getXianhuaHuayu() {
        return xianhuaHuayu;
    }


    /**
	 * 设置：花语
	 */
    public void setXianhuaHuayu(String xianhuaHuayu) {
        this.xianhuaHuayu = xianhuaHuayu;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 设置：赞
	 */
    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 设置：踩
	 */
    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：鲜花类型
	 */
    public Integer getXianhuaTypes() {
        return xianhuaTypes;
    }


    /**
	 * 设置：鲜花类型
	 */
    public void setXianhuaTypes(Integer xianhuaTypes) {
        this.xianhuaTypes = xianhuaTypes;
    }
    /**
	 * 获取：鲜花数量
	 */
    public Integer getXianhuaKucunNumber() {
        return xianhuaKucunNumber;
    }


    /**
	 * 设置：鲜花数量
	 */
    public void setXianhuaKucunNumber(Integer xianhuaKucunNumber) {
        this.xianhuaKucunNumber = xianhuaKucunNumber;
    }
    /**
	 * 获取：鲜花原价
	 */
    public Double getXianhuaOldMoney() {
        return xianhuaOldMoney;
    }


    /**
	 * 设置：鲜花原价
	 */
    public void setXianhuaOldMoney(Double xianhuaOldMoney) {
        this.xianhuaOldMoney = xianhuaOldMoney;
    }
    /**
	 * 获取：现价
	 */
    public Double getXianhuaNewMoney() {
        return xianhuaNewMoney;
    }


    /**
	 * 设置：现价
	 */
    public void setXianhuaNewMoney(Double xianhuaNewMoney) {
        this.xianhuaNewMoney = xianhuaNewMoney;
    }
    /**
	 * 获取：鲜花热度
	 */
    public Integer getXianhuaClicknum() {
        return xianhuaClicknum;
    }


    /**
	 * 设置：鲜花热度
	 */
    public void setXianhuaClicknum(Integer xianhuaClicknum) {
        this.xianhuaClicknum = xianhuaClicknum;
    }
    /**
	 * 获取：鲜花介绍
	 */
    public String getXianhuaContent() {
        return xianhuaContent;
    }


    /**
	 * 设置：鲜花介绍
	 */
    public void setXianhuaContent(String xianhuaContent) {
        this.xianhuaContent = xianhuaContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getXianhuaDelete() {
        return xianhuaDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setXianhuaDelete(Integer xianhuaDelete) {
        this.xianhuaDelete = xianhuaDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
