package io.renren.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 14:25:28
 */
@TableName("nideshop_goods")
public class NideshopGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 所属分类
	 */
	@NotNull(message="分类信息不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer categoryId;

	/**
	 * 所属分类名称
	 */
	@TableField(exist=false)
	private String categoryName;
	/**
	 * 商品编码
	 */
	private String goodsSn;
	/**
	 * 商品名称
	 */
	@NotBlank(message="商品名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 所属品牌
	 */
	@NotNull(message="品牌信息不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer brandId;

	/**
	 * 品牌名称
	 */
	@TableField(exist=false)
	private String brandName;
	/**
	 * 
	 */
	private Integer goodsNumber;
	/**
	 * 
	 */
	private String keywords;
	/**
	 * 商品摘要
	 */
	@NotBlank(message="商品摘要不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String goodsBrief;
	/**
	 * 商品内容
	 */
	@NotBlank(message="商品内容不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String goodsDesc;
	/**
	 * 
	 */
	private Integer isOnSale;
	/**
	 * 
	 */
	private Integer addTime;
	/**
	 * 排序编号
	 */
	private Integer sortOrder;
	/**
	 * 是否删除
	 */
	@TableLogic(delval = "1",value = "0")
	private Integer isDelete;
	/**
	 * 
	 */
	private Integer attributeCategory;
	/**
	 * 专柜价格
	 */
	private BigDecimal counterPrice;
	/**
	 * 附加价格
	 */
	private BigDecimal extraPrice;
	/**
	 * 
	 */
	private Integer isNew;
	/**
	 * 商品单位
	 */
	private String goodsUnit;
	/**
	 * 商品主图
	 */
	private String primaryPicUrl;
	/**
	 * 商品列表图
	 */
	@NotBlank(message="商品配图不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String listPicUrl;
	/**
	 * 零售价格
	 */
	private BigDecimal retailPrice;
	/**
	 * 销售量
	 */
	private Integer sellVolume;
	/**
	 * 主sku　product_id
	 */
	private Integer primaryProductId;
	/**
	 * 单位价格，单价
	 */
	private BigDecimal unitPrice;
	/**
	 * 
	 */
	private String promotionDesc;
	/**
	 * 
	 */
	private String promotionTag;
	/**
	 * APP专享价
	 */
	private BigDecimal appExclusivePrice;
	/**
	 * 是否是APP专属
	 */
	private Integer isAppExclusive;
	/**
	 * 
	 */
	private Integer isLimited;
	/**
	 * 
	 */
	private Integer isHot;

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
	 * 设置：所属分类
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：所属分类
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * 设置：商品编码
	 */
	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}
	/**
	 * 获取：商品编码
	 */
	public String getGoodsSn() {
		return goodsSn;
	}
	/**
	 * 设置：商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：商品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：所属品牌
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：所属品牌
	 */
	public Integer getBrandId() {
		return brandId;
	}
	/**
	 * 设置：
	 */
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	/**
	 * 获取：
	 */
	public Integer getGoodsNumber() {
		return goodsNumber;
	}
	/**
	 * 设置：
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * 获取：
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * 设置：商品摘要
	 */
	public void setGoodsBrief(String goodsBrief) {
		this.goodsBrief = goodsBrief;
	}
	/**
	 * 获取：商品摘要
	 */
	public String getGoodsBrief() {
		return goodsBrief;
	}
	/**
	 * 设置：商品内容
	 */
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	/**
	 * 获取：商品内容
	 */
	public String getGoodsDesc() {
		return goodsDesc;
	}
	/**
	 * 设置：
	 */
	public void setIsOnSale(Integer isOnSale) {
		this.isOnSale = isOnSale;
	}
	/**
	 * 获取：
	 */
	public Integer getIsOnSale() {
		return isOnSale;
	}
	/**
	 * 设置：
	 */
	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：
	 */
	public Integer getAddTime() {
		return addTime;
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
	/**
	 * 设置：是否删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：
	 */
	public void setAttributeCategory(Integer attributeCategory) {
		this.attributeCategory = attributeCategory;
	}
	/**
	 * 获取：
	 */
	public Integer getAttributeCategory() {
		return attributeCategory;
	}
	/**
	 * 设置：专柜价格
	 */
	public void setCounterPrice(BigDecimal counterPrice) {
		this.counterPrice = counterPrice;
	}
	/**
	 * 获取：专柜价格
	 */
	public BigDecimal getCounterPrice() {
		return counterPrice;
	}
	/**
	 * 设置：附加价格
	 */
	public void setExtraPrice(BigDecimal extraPrice) {
		this.extraPrice = extraPrice;
	}
	/**
	 * 获取：附加价格
	 */
	public BigDecimal getExtraPrice() {
		return extraPrice;
	}
	/**
	 * 设置：
	 */
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	/**
	 * 获取：
	 */
	public Integer getIsNew() {
		return isNew;
	}
	/**
	 * 设置：商品单位
	 */
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	/**
	 * 获取：商品单位
	 */
	public String getGoodsUnit() {
		return goodsUnit;
	}
	/**
	 * 设置：商品主图
	 */
	public void setPrimaryPicUrl(String primaryPicUrl) {
		this.primaryPicUrl = primaryPicUrl;
	}
	/**
	 * 获取：商品主图
	 */
	public String getPrimaryPicUrl() {
		return primaryPicUrl;
	}
	/**
	 * 设置：商品列表图
	 */
	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}
	/**
	 * 获取：商品列表图
	 */
	public String getListPicUrl() {
		return listPicUrl;
	}
	/**
	 * 设置：零售价格
	 */
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	/**
	 * 获取：零售价格
	 */
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	/**
	 * 设置：销售量
	 */
	public void setSellVolume(Integer sellVolume) {
		this.sellVolume = sellVolume;
	}
	/**
	 * 获取：销售量
	 */
	public Integer getSellVolume() {
		return sellVolume;
	}
	/**
	 * 设置：主sku　product_id
	 */
	public void setPrimaryProductId(Integer primaryProductId) {
		this.primaryProductId = primaryProductId;
	}
	/**
	 * 获取：主sku　product_id
	 */
	public Integer getPrimaryProductId() {
		return primaryProductId;
	}
	/**
	 * 设置：单位价格，单价
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * 获取：单位价格，单价
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	/**
	 * 设置：
	 */
	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}
	/**
	 * 获取：
	 */
	public String getPromotionDesc() {
		return promotionDesc;
	}
	/**
	 * 设置：
	 */
	public void setPromotionTag(String promotionTag) {
		this.promotionTag = promotionTag;
	}
	/**
	 * 获取：
	 */
	public String getPromotionTag() {
		return promotionTag;
	}
	/**
	 * 设置：APP专享价
	 */
	public void setAppExclusivePrice(BigDecimal appExclusivePrice) {
		this.appExclusivePrice = appExclusivePrice;
	}
	/**
	 * 获取：APP专享价
	 */
	public BigDecimal getAppExclusivePrice() {
		return appExclusivePrice;
	}
	/**
	 * 设置：是否是APP专属
	 */
	public void setIsAppExclusive(Integer isAppExclusive) {
		this.isAppExclusive = isAppExclusive;
	}
	/**
	 * 获取：是否是APP专属
	 */
	public Integer getIsAppExclusive() {
		return isAppExclusive;
	}
	/**
	 * 设置：
	 */
	public void setIsLimited(Integer isLimited) {
		this.isLimited = isLimited;
	}
	/**
	 * 获取：
	 */
	public Integer getIsLimited() {
		return isLimited;
	}
	/**
	 * 设置：
	 */
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	/**
	 * 获取：
	 */
	public Integer getIsHot() {
		return isHot;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
