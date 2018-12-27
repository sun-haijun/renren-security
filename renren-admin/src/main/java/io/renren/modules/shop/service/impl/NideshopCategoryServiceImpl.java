package io.renren.modules.shop.service.impl;

import io.renren.common.utils.Constant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.NideshopCategoryDao;
import io.renren.modules.shop.entity.NideshopCategoryEntity;
import io.renren.modules.shop.service.NideshopCategoryService;


@Service("nideshopCategoryService")
public class NideshopCategoryServiceImpl extends ServiceImpl<NideshopCategoryDao, NideshopCategoryEntity> implements NideshopCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NideshopCategoryEntity> page = this.selectPage(
                new Query<NideshopCategoryEntity>(params).getPage(),
                new EntityWrapper<NideshopCategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<NideshopCategoryEntity> queryList(Map<String, Object> params) {
        List<NideshopCategoryEntity> deptList =
                this.selectList(new EntityWrapper<NideshopCategoryEntity>()
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER)));

        for(NideshopCategoryEntity shopCategoryEntity : deptList){
            NideshopCategoryEntity parentEntity =  this.selectById(shopCategoryEntity.getParentId());
            if(parentEntity != null){
                shopCategoryEntity.setParentName(parentEntity.getName());
            }
        }
        return deptList;
    }

    @Override
    public List<Integer> queryCategoryList(Integer parentId) {
        return baseMapper.queryCategoryList(parentId);
    }
}
