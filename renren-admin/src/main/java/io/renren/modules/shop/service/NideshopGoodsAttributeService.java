package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.NideshopGoodsAttributeEntity;

import java.util.Map;

/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 19:02:09
 */
public interface NideshopGoodsAttributeService extends IService<NideshopGoodsAttributeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

