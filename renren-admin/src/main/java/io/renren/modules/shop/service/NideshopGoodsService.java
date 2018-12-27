package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.NideshopGoodsEntity;

import java.util.Map;

/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 14:25:28
 */
public interface NideshopGoodsService extends IService<NideshopGoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

