package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.NideshopAdEntity;

import java.util.Map;

/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-24 11:28:42
 */
public interface NideshopAdService extends IService<NideshopAdEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

