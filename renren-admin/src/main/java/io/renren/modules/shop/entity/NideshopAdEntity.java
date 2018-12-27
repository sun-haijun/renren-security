package io.renren.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 
 * 
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-24 11:28:42
 */
@TableName("nideshop_ad")
public class NideshopAdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@TableId
	private Integer id;

	/**
	 * 
	 */
	private Integer adPositionId;
	/**
	 * 
	 */
	private Integer mediaType;

	/**
	 * 广告名称
	 */
	@NotBlank(message="广告名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;

	private String link;
	/**
	 * 
	 */
	@NotBlank(message="广告配图不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String imageUrl;
	/**
	 * 
	 */
	@NotBlank(message="广告摘要不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String content;

	/**
	 * 跳转方式
	 */
	@TableField(exist=false)
	private String linkType;
	/**
	 * 跳转id
	 */
	@TableField(exist=false)
	private Integer linkId;

	/**
	 * 跳转id
	 */
	@NotBlank(message="开始时间不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@TableField(exist=false)
	private String adStartTime;

	/**
	 * 跳转id
	 */
	@NotBlank(message="结束时间不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@TableField(exist=false)
	private String adEndTime;

	/**
	 *
	 */
	private Integer startTime;
	/**
	 * 
	 */
	private Integer endTime;
	/**
	 * 
	 */
	@TableLogic(delval = "0",value = "1")
	private Integer enabled;

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
	public void setAdPositionId(Integer adPositionId) {
		this.adPositionId = adPositionId;
	}
	/**
	 * 获取：
	 */
	public Integer getAdPositionId() {
		return adPositionId;
	}
	/**
	 * 设置：
	 */
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}
	/**
	 * 获取：
	 */
	public Integer getMediaType() {
		return mediaType;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * 获取：
	 */
	public String getLink() {
		return link;
	}
	/**
	 * 设置：
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 获取：
	 */
	public String getImageUrl() {
		return imageUrl;
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
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Integer getEndTime() {
		return endTime;
	}
	/**
	 * 设置：
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	/**
	 * 获取：
	 */
	public Integer getEnabled() {
		return enabled;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public Integer getLinkId() {
		return linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	public String getAdStartTime() {
		return adStartTime;
	}

	public void setAdStartTime(String adStartTime) {
		this.adStartTime = adStartTime;
	}

	public String getAdEndTime() {
		return adEndTime;
	}

	public void setAdEndTime(String adEndTime) {
		this.adEndTime = adEndTime;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}
}
