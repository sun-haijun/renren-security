package io.renren.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * @date 2018-12-27 11:07:56
 */
@TableName("nideshop_category")
public class NideshopCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 分类名称
	 */
	@NotBlank(message="分类名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 分类关键字
	 */
	private String keywords;

	private String frontDesc;

	/**
	 * 所属父节点分类
	 */
	@NotNull(message="分类名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer parentId;

	//上级部门名称
	@TableField(exist=false)
	private String parentName;
	/**
	 * 排序号
	 */
	private Integer sortOrder;
	/**
	 * 
	 */
	private Integer showIndex;
	/**
	 * 是否有效
	 */
	@TableLogic(delval = "0",value = "1")
	private Integer isShow;
	/**
	 * 
	 */
	private String bannerUrl;
	/**
	 * 
	 */
	private String iconUrl;
	/**
	 * 
	 */
	private String imgUrl;
	/**
	 * 分类配图
	 */
	@NotBlank(message="分类配图不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String wapBannerUrl;
	/**
	 * 
	 */
	private String level;
	/**
	 * 
	 */
	private Integer type;
	/**
	 * 分类描述
	 */
	@NotBlank(message="分类介绍不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String frontName;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分类名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：分类关键字
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * 获取：分类关键字
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * 设置：分类摘要
	 */
	public void setFrontDesc(String frontDesc) {
		this.frontDesc = frontDesc;
	}
	/**
	 * 获取：分类摘要
	 */
	public String getFrontDesc() {
		return frontDesc;
	}
	/**
	 * 设置：所属父节点分类
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：所属父节点分类
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：排序号
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获取：排序号
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}
	/**
	 * 设置：
	 */
	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}
	/**
	 * 获取：
	 */
	public Integer getShowIndex() {
		return showIndex;
	}
	/**
	 * 设置：是否有效
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	/**
	 * 获取：是否有效
	 */
	public Integer getIsShow() {
		return isShow;
	}
	/**
	 * 设置：
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	/**
	 * 获取：
	 */
	public String getBannerUrl() {
		return bannerUrl;
	}
	/**
	 * 设置：
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	/**
	 * 获取：
	 */
	public String getIconUrl() {
		return iconUrl;
	}
	/**
	 * 设置：
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置：分类配图
	 */
	public void setWapBannerUrl(String wapBannerUrl) {
		this.wapBannerUrl = wapBannerUrl;
	}
	/**
	 * 获取：分类配图
	 */
	public String getWapBannerUrl() {
		return wapBannerUrl;
	}
	/**
	 * 设置：
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取：
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置：
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：分类描述
	 */
	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}
	/**
	 * 获取：分类描述
	 */
	public String getFrontName() {
		return frontName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
