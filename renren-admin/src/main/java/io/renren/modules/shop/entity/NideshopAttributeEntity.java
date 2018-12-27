package io.renren.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 14:04:06
 */
@TableName("nideshop_attribute")
public class NideshopAttributeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Integer id;
	/**
	 * 编号
	 */
	private Integer attributeCategoryId;
	/**
	 * 名称
	 */
	@NotBlank(message="参数配置名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 类型
	 */
	private Integer inputType;
	/**
	 * 
	 */
	private String values;
	/**
	 * 排序
	 */
	@NotNull(message="排序编号不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer sortOrder;

	/**
	 * 是否有效
	 */
	@TableLogic(delval = "1",value = "0")
	private String isDelete;

	/**
	 * 设置：主键id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：编号
	 */
	public void setAttributeCategoryId(Integer attributeCategoryId) {
		this.attributeCategoryId = attributeCategoryId;
	}
	/**
	 * 获取：编号
	 */
	public Integer getAttributeCategoryId() {
		return attributeCategoryId;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：类型
	 */
	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}
	/**
	 * 获取：类型
	 */
	public Integer getInputType() {
		return inputType;
	}
	/**
	 * 设置：
	 */
	public void setValues(String values) {
		this.values = values;
	}
	/**
	 * 获取：
	 */
	public String getValues() {
		return values;
	}
	/**
	 * 设置：排序
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}
	/**
	 * 设置：是否有效
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否有效
	 */
	public String getIsDelete() {
		return isDelete;
	}
}
