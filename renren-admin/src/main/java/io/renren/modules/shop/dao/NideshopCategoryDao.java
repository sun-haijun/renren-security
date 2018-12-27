package io.renren.modules.shop.dao;

import io.renren.modules.shop.entity.NideshopCategoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 
 * 
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 11:07:56
 */
public interface NideshopCategoryDao extends BaseMapper<NideshopCategoryEntity> {
    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Integer> queryCategoryList(Integer parentId);
}
