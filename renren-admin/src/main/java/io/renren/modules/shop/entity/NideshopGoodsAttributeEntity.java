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
 * @date 2018-12-27 14:25:27
 */
@TableName("nideshop_goods_attribute")
public class NideshopGoodsAttributeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 商品id
	 */
	@NotNull(message="配置产品不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer goodsId;

	/**
	 * 商品名称
	 */
	@TableField(exist=false)
	private String goodsName;

	/**
	 * 参数id
	 */
	@NotNull(message="配置项不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer attributeId;

	/**
	 * 配置类型说明
	 */
	@TableField(exist=false)
	private String attributeName;
	/**
	 * 配置说明
	 */
	@NotBlank(message="配置说明不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String value;

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
	 * 设置：参数id
	 */
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}
	/**
	 * 获取：参数id
	 */
	public Integer getAttributeId() {
		return attributeId;
	}
	/**
	 * 设置：配置说明
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：配置说明
	 */
	public String getValue() {
		return value;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
