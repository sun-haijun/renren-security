package io.renren.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-28 11:23:43
 */
@TableName("nideshop_goods_gallery")
public class NideshopGoodsGalleryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 商品id
	 */
	@NotNull(message="商品信息不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer goodsId;
	/**
	 * 配图地址
	 */
	@NotBlank(message="轮播图信息不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String imgUrl;

	/**
	 * 商品名称
	 */
	@TableField(exist=false)
	private String goodsName;
	/**
	 * 
	 */
	private String imgDesc;
	/**
	 * 排序编号
	 */
	private Integer sortOrder;

	/**
	 * 是否有效
	 */
	@TableLogic(delval = "1",value = "0")
	private Integer isDelete;

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
	 * 设置：商品id
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品id
	 */
	public Integer getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：配图地址
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：配图地址
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置：
	 */
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}
	/**
	 * 获取：
	 */
	public String getImgDesc() {
		return imgDesc;
	}
	/**
	 * 设置：排序编号
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获取：排序编号
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
