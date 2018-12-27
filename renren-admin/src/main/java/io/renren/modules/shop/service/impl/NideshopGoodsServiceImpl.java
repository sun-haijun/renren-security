package io.renren.modules.shop.service.impl;

import io.renren.modules.shop.entity.NideshopBrandEntity;
import io.renren.modules.shop.entity.NideshopCategoryEntity;
import io.renren.modules.shop.service.NideshopBrandService;
import io.renren.modules.shop.service.NideshopCategoryService;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.NideshopGoodsDao;
import io.renren.modules.shop.entity.NideshopGoodsEntity;
import io.renren.modules.shop.service.NideshopGoodsService;

import javax.annotation.Resource;


@Service("nideshopGoodsService")
public class NideshopGoodsServiceImpl extends ServiceImpl<NideshopGoodsDao, NideshopGoodsEntity> implements NideshopGoodsService {

    @Resource
    private NideshopBrandService nideshopBrandService;

    @Resource
    private NideshopCategoryService nideshopCategoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NideshopGoodsEntity> page = this.selectPage(
                new Query<NideshopGoodsEntity>(params).getPage(),
                new EntityWrapper<NideshopGoodsEntity>()
        );

        for (NideshopGoodsEntity goods : page.getRecords()) {
            NideshopBrandEntity brandEntity = nideshopBrandService.selectById(goods.getBrandId());
            if (brandEntity != null) {
                goods.setBrandName(brandEntity.getName());
            }

            NideshopCategoryEntity categoryEntity = nideshopCategoryService.selectById(goods.getCategoryId());
            if (categoryEntity != null) {
                goods.setCategoryName(categoryEntity.getName());
            }
        }
        return new PageUtils(page);
    }

}
