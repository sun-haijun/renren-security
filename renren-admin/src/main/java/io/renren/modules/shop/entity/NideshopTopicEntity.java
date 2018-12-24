package io.renren.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-24 18:54:33
 */
@TableName("nideshop_topic")
public class NideshopTopicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String avatar;
	/**
	 * 
	 */
	private String itemPicUrl;
	/**
	 * 
	 */
	private String subtitle;
	/**
	 * 
	 */
	private Integer topicCategoryId;
	/**
	 * 
	 */
	private BigDecimal priceInfo;
	/**
	 * 
	 */
	private String readCount;
	/**
	 * 
	 */
	private String scenePicUrl;
	/**
	 * 
	 */
	private Integer topicTemplateId;
	/**
	 * 
	 */
	private Integer topicTagId;
	/**
	 * 
	 */
	private Integer sortOrder;
	/**
	 * 
	 */
	private Integer isShow;
	/**
	 * 
	 */
	private String address;

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
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取：
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * 设置：
	 */
	public void setItemPicUrl(String itemPicUrl) {
		this.itemPicUrl = itemPicUrl;
	}
	/**
	 * 获取：
	 */
	public String getItemPicUrl() {
		return itemPicUrl;
	}
	/**
	 * 设置：
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * 获取：
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * 设置：
	 */
	public void setTopicCategoryId(Integer topicCategoryId) {
		this.topicCategoryId = topicCategoryId;
	}
	/**
	 * 获取：
	 */
	public Integer getTopicCategoryId() {
		return topicCategoryId;
	}
	/**
	 * 设置：
	 */
	public void setPriceInfo(BigDecimal priceInfo) {
		this.priceInfo = priceInfo;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPriceInfo() {
		return priceInfo;
	}
	/**
	 * 设置：
	 */
	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	/**
	 * 获取：
	 */
	public String getReadCount() {
		return readCount;
	}
	/**
	 * 设置：
	 */
	public void setScenePicUrl(String scenePicUrl) {
		this.scenePicUrl = scenePicUrl;
	}
	/**
	 * 获取：
	 */
	public String getScenePicUrl() {
		return scenePicUrl;
	}
	/**
	 * 设置：
	 */
	public void setTopicTemplateId(Integer topicTemplateId) {
		this.topicTemplateId = topicTemplateId;
	}
	/**
	 * 获取：
	 */
	public Integer getTopicTemplateId() {
		return topicTemplateId;
	}
	/**
	 * 设置：
	 */
	public void setTopicTagId(Integer topicTagId) {
		this.topicTagId = topicTagId;
	}
	/**
	 * 获取：
	 */
	public Integer getTopicTagId() {
		return topicTagId;
	}
	/**
	 * 设置：
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获取：
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}
	/**
	 * 设置：
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	/**
	 * 获取：
	 */
	public Integer getIsShow() {
		return isShow;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
}
