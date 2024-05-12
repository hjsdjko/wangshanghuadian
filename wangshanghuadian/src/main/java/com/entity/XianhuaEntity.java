package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 鲜花
 *
 * @author 
 * @email
 */
@TableName("xianhua")
public class XianhuaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XianhuaEntity() {

	}

	public XianhuaEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 鲜花名称
     */
    @ColumnInfo(comment="鲜花名称",type="varchar(200)")
    @TableField(value = "xianhua_name")

    private String xianhuaName;


    /**
     * 鲜花编号
     */
    @ColumnInfo(comment="鲜花编号",type="varchar(200)")
    @TableField(value = "xianhua_uuid_number")

    private String xianhuaUuidNumber;


    /**
     * 鲜花照片
     */
    @ColumnInfo(comment="鲜花照片",type="varchar(200)")
    @TableField(value = "xianhua_photo")

    private String xianhuaPhoto;


    /**
     * 花语
     */
    @ColumnInfo(comment="花语",type="varchar(200)")
    @TableField(value = "xianhua_huayu")

    private String xianhuaHuayu;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 鲜花类型
     */
    @ColumnInfo(comment="鲜花类型",type="int(11)")
    @TableField(value = "xianhua_types")

    private Integer xianhuaTypes;


    /**
     * 鲜花数量
     */
    @ColumnInfo(comment="鲜花数量",type="int(11)")
    @TableField(value = "xianhua_kucun_number")

    private Integer xianhuaKucunNumber;


    /**
     * 鲜花原价
     */
    @ColumnInfo(comment="鲜花原价",type="decimal(10,2)")
    @TableField(value = "xianhua_old_money")

    private Double xianhuaOldMoney;


    /**
     * 现价
     */
    @ColumnInfo(comment="现价",type="decimal(10,2)")
    @TableField(value = "xianhua_new_money")

    private Double xianhuaNewMoney;


    /**
     * 鲜花热度
     */
    @ColumnInfo(comment="鲜花热度",type="int(11)")
    @TableField(value = "xianhua_clicknum")

    private Integer xianhuaClicknum;


    /**
     * 鲜花介绍
     */
    @ColumnInfo(comment="鲜花介绍",type="longtext")
    @TableField(value = "xianhua_content")

    private String xianhuaContent;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "xianhua_delete")

    private Integer xianhuaDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Xianhua{" +
            ", id=" + id +
            ", xianhuaName=" + xianhuaName +
            ", xianhuaUuidNumber=" + xianhuaUuidNumber +
            ", xianhuaPhoto=" + xianhuaPhoto +
            ", xianhuaHuayu=" + xianhuaHuayu +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", xianhuaTypes=" + xianhuaTypes +
            ", xianhuaKucunNumber=" + xianhuaKucunNumber +
            ", xianhuaOldMoney=" + xianhuaOldMoney +
            ", xianhuaNewMoney=" + xianhuaNewMoney +
            ", xianhuaClicknum=" + xianhuaClicknum +
            ", xianhuaContent=" + xianhuaContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", xianhuaDelete=" + xianhuaDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
