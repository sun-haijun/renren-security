package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.NideshopAttributeEntity;

import java.util.Map;

/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 14:04:06
 */
public interface NideshopAttributeService extends IService<NideshopAttributeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

