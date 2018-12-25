package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.NideshopBrandEntity;

import java.util.Map;

/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-25 17:19:14
 */
public interface NideshopBrandService extends IService<NideshopBrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

