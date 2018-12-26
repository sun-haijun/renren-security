package io.renren.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-25 17:19:14
 */
@TableName("nideshop_brand")
public class NideshopBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 品牌名称
	 */
	@NotBlank(message="品牌名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 配图
	 */
	private String listPicUrl;
	/**
	 * 品牌介绍
	 */
	@NotBlank(message="品牌介绍不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String simpleDesc;
	/**
	 * 品牌介绍
	 */
	private String picUrl;
	/**
	 * 品牌排序
	 */
	private Integer sortOrder;
	/**
	 * 是否有效0无效1有效
	 */
	@TableLogic(delval = "0",value = "1")
	private Integer isShow;
	/**
	 * 品牌起步价
	 */
	@NotBlank(message="品牌起步价不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal floorPrice;
	/**
	 * 列表配图
	 */
	@NotBlank(message="品牌列表配图不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String appListPicUrl;
	/**
	 * 是否推荐0否1推荐
	 */
	private Integer isNew;
	/**
	 * 首页配图
	 */
	@NotBlank(message="品牌首页配图不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String newPicUrl;
	/**
	 * 临时变量
	 */
	private Integer newSortOrder;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：品牌名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：品牌名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：配图
	 */
	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}
	/**
	 * 获取：配图
	 */
	public String getListPicUrl() {
		return listPicUrl;
	}
	/**
	 * 设置：品牌介绍
	 */
	public void setSimpleDesc(String simpleDesc) {
		this.simpleDesc = simpleDesc;
	}
	/**
	 * 获取：品牌介绍
	 */
	public String getSimpleDesc() {
		return simpleDesc;
	}
	/**
	 * 设置：品牌介绍
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * 获取：品牌介绍
	 */
	public String getPicUrl() {
		return picUrl;
	}
	/**
	 * 设置：品牌排序
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获取：品牌排序
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}
	/**
	 * 设置：是否有效0无效1有效
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	/**
	 * 获取：是否有效0无效1有效
	 */
	public Integer getIsShow() {
		return isShow;
	}
	/**
	 * 设置：品牌起步价
	 */
	public void setFloorPrice(BigDecimal floorPrice) {
		this.floorPrice = floorPrice;
	}
	/**
	 * 获取：品牌起步价
	 */
	public BigDecimal getFloorPrice() {
		return floorPrice;
	}
	/**
	 * 设置：列表配图
	 */
	public void setAppListPicUrl(String appListPicUrl) {
		this.appListPicUrl = appListPicUrl;
	}
	/**
	 * 获取：列表配图
	 */
	public String getAppListPicUrl() {
		return appListPicUrl;
	}
	/**
	 * 设置：是否推荐0否1推荐
	 */
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	/**
	 * 获取：是否推荐0否1推荐
	 */
	public Integer getIsNew() {
		return isNew;
	}
	/**
	 * 设置：首页配图
	 */
	public void setNewPicUrl(String newPicUrl) {
		this.newPicUrl = newPicUrl;
	}
	/**
	 * 获取：首页配图
	 */
	public String getNewPicUrl() {
		return newPicUrl;
	}
	/**
	 * 设置：临时变量
	 */
	public void setNewSortOrder(Integer newSortOrder) {
		this.newSortOrder = newSortOrder;
	}
	/**
	 * 获取：临时变量
	 */
	public Integer getNewSortOrder() {
		return newSortOrder;
	}
}
