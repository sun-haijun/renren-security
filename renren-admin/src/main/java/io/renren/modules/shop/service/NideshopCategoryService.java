package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.NideshopCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 11:07:56
 */
public interface NideshopCategoryService extends IService<NideshopCategoryEntity> {

    List<NideshopCategoryEntity> queryList(Map<String, Object> map);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Integer> queryCategoryList(Integer parentId);

    PageUtils queryPage(Map<String, Object> params);
}

