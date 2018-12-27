package io.renren.modules.shop.service.impl;

import io.renren.modules.shop.entity.NideshopAttributeEntity;
import io.renren.modules.shop.entity.NideshopGoodsEntity;
import io.renren.modules.shop.service.NideshopAttributeService;
import io.renren.modules.shop.service.NideshopGoodsService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.NideshopGoodsAttributeDao;
import io.renren.modules.shop.entity.NideshopGoodsAttributeEntity;
import io.renren.modules.shop.service.NideshopGoodsAttributeService;

import javax.annotation.Resource;


@Service("nideshopGoodsAttributeService")
public class NideshopGoodsAttributeServiceImpl extends ServiceImpl<NideshopGoodsAttributeDao, NideshopGoodsAttributeEntity> implements NideshopGoodsAttributeService {

    @Resource
    private NideshopGoodsService nideshopGoodsService;

    @Resource
    private NideshopAttributeService nideshopAttributeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NideshopGoodsAttributeEntity> page = this.selectPage(
                new Query<NideshopGoodsAttributeEntity>(params).getPage(),
                new EntityWrapper<NideshopGoodsAttributeEntity>()
        );

        for (NideshopGoodsAttributeEntity attr : page.getRecords()) {
            NideshopGoodsEntity goodsEntity = nideshopGoodsService.selectById(attr.getGoodsId());
            if(goodsEntity!=null){
                attr.setGoodsName(goodsEntity.getName());
            }
            NideshopAttributeEntity entity = nideshopAttributeService.selectById(attr.getAttributeId());
            if(entity!=null){
                attr.setAttributeName(entity.getName());
            }
        }
        return new PageUtils(page);
    }

}
