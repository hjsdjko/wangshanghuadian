package com.entity.vo;

import com.entity.XianhuaEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 鲜花
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xianhua")
public class XianhuaVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 鲜花名称
     */

    @TableField(value = "xianhua_name")
    private String xianhuaName;


    /**
     * 鲜花编号
     */

    @TableField(value = "xianhua_uuid_number")
    private String xianhuaUuidNumber;


    /**
     * 鲜花照片
     */

    @TableField(value = "xianhua_photo")
    private String xianhuaPhoto;


    /**
     * 花语
     */

    @TableField(value = "xianhua_huayu")
    private String xianhuaHuayu;


    /**
     * 赞
     */

    @TableField(value = "zan_number")
    private Integer zanNumber;


    /**
     * 踩
     */

    @TableField(value = "cai_number")
    private Integer caiNumber;


    /**
     * 鲜花类型
     */

    @TableField(value = "xianhua_types")
    private Integer xianhuaTypes;


    /**
     * 鲜花数量
     */

    @TableField(value = "xianhua_kucun_number")
    private Integer xianhuaKucunNumber;


    /**
     * 鲜花原价
     */

    @TableField(value = "xianhua_old_money")
    private Double xianhuaOldMoney;


    /**
     * 现价
     */

    @TableField(value = "xianhua_new_money")
    private Double xianhuaNewMoney;


    /**
     * 鲜花热度
     */

    @TableField(value = "xianhua_clicknum")
    private Integer xianhuaClicknum;


    /**
     * 鲜花介绍
     */

    @TableField(value = "xianhua_content")
    private String xianhuaContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "xianhua_delete")
    private Integer xianhuaDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：鲜花名称
	 */
    public String getXianhuaName() {
        return xianhuaName;
    }


    /**
	 * 获取：鲜花名称
	 */

    public void setXianhuaName(String xianhuaName) {
        this.xianhuaName = xianhuaName;
    }
    /**
	 * 设置：鲜花编号
	 */
    public String getXianhuaUuidNumber() {
        return xianhuaUuidNumber;
    }


    /**
	 * 获取：鲜花编号
	 */

    public void setXianhuaUuidNumber(String xianhuaUuidNumber) {
        this.xianhuaUuidNumber = xianhuaUuidNumber;
    }
    /**
	 * 设置：鲜花照片
	 */
    public String getXianhuaPhoto() {
        return xianhuaPhoto;
    }


    /**
	 * 获取：鲜花照片
	 */

    public void setXianhuaPhoto(String xianhuaPhoto) {
        this.xianhuaPhoto = xianhuaPhoto;
    }
    /**
	 * 设置：花语
	 */
    public String getXianhuaHuayu() {
        return xianhuaHuayu;
    }


    /**
	 * 获取：花语
	 */

    public void setXianhuaHuayu(String xianhuaHuayu) {
        this.xianhuaHuayu = xianhuaHuayu;
    }
    /**
	 * 设置：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 获取：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 设置：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 获取：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 设置：鲜花类型
	 */
    public Integer getXianhuaTypes() {
        return xianhuaTypes;
    }


    /**
	 * 获取：鲜花类型
	 */

    public void setXianhuaTypes(Integer xianhuaTypes) {
        this.xianhuaTypes = xianhuaTypes;
    }
    /**
	 * 设置：鲜花数量
	 */
    public Integer getXianhuaKucunNumber() {
        return xianhuaKucunNumber;
    }


    /**
	 * 获取：鲜花数量
	 */

    public void setXianhuaKucunNumber(Integer xianhuaKucunNumber) {
        this.xianhuaKucunNumber = xianhuaKucunNumber;
    }
    /**
	 * 设置：鲜花原价
	 */
    public Double getXianhuaOldMoney() {
        return xianhuaOldMoney;
    }


    /**
	 * 获取：鲜花原价
	 */

    public void setXianhuaOldMoney(Double xianhuaOldMoney) {
        this.xianhuaOldMoney = xianhuaOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getXianhuaNewMoney() {
        return xianhuaNewMoney;
    }


    /**
	 * 获取：现价
	 */

    public void setXianhuaNewMoney(Double xianhuaNewMoney) {
        this.xianhuaNewMoney = xianhuaNewMoney;
    }
    /**
	 * 设置：鲜花热度
	 */
    public Integer getXianhuaClicknum() {
        return xianhuaClicknum;
    }


    /**
	 * 获取：鲜花热度
	 */

    public void setXianhuaClicknum(Integer xianhuaClicknum) {
        this.xianhuaClicknum = xianhuaClicknum;
    }
    /**
	 * 设置：鲜花介绍
	 */
    public String getXianhuaContent() {
        return xianhuaContent;
    }


    /**
	 * 获取：鲜花介绍
	 */

    public void setXianhuaContent(String xianhuaContent) {
        this.xianhuaContent = xianhuaContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getXianhuaDelete() {
        return xianhuaDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setXianhuaDelete(Integer xianhuaDelete) {
        this.xianhuaDelete = xianhuaDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
