package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.XianhuaEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 鲜花
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("xianhua")
public class XianhuaView extends XianhuaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 鲜花类型的值
	*/
	@ColumnInfo(comment="鲜花类型的字典表值",type="varchar(200)")
	private String xianhuaValue;
	/**
	* 是否上架的值
	*/
	@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
	private String shangxiaValue;




	public XianhuaView() {

	}

	public XianhuaView(XianhuaEntity xianhuaEntity) {
		try {
			BeanUtils.copyProperties(this, xianhuaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 鲜花类型的值
	*/
	public String getXianhuaValue() {
		return xianhuaValue;
	}
	/**
	* 设置： 鲜花类型的值
	*/
	public void setXianhuaValue(String xianhuaValue) {
		this.xianhuaValue = xianhuaValue;
	}
	//当前表的
	/**
	* 获取： 是否上架的值
	*/
	public String getShangxiaValue() {
		return shangxiaValue;
	}
	/**
	* 设置： 是否上架的值
	*/
	public void setShangxiaValue(String shangxiaValue) {
		this.shangxiaValue = shangxiaValue;
	}




	@Override
	public String toString() {
		return "XianhuaView{" +
			", xianhuaValue=" + xianhuaValue +
			", shangxiaValue=" + shangxiaValue +
			"} " + super.toString();
	}
}
