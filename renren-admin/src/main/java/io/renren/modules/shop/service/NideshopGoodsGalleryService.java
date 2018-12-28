package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.NideshopGoodsGalleryEntity;

import java.util.Map;

/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-28 11:23:43
 */
public interface NideshopGoodsGalleryService extends IService<NideshopGoodsGalleryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

