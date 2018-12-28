package io.renren.modules.shop.service.impl;

import io.renren.modules.shop.entity.NideshopGoodsEntity;
import io.renren.modules.shop.service.NideshopGoodsService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.NideshopGoodsGalleryDao;
import io.renren.modules.shop.entity.NideshopGoodsGalleryEntity;
import io.renren.modules.shop.service.NideshopGoodsGalleryService;

import javax.annotation.Resource;


@Service("nideshopGoodsGalleryService")
public class NideshopGoodsGalleryServiceImpl extends ServiceImpl<NideshopGoodsGalleryDao, NideshopGoodsGalleryEntity> implements NideshopGoodsGalleryService {

    @Resource
    private NideshopGoodsService nideshopGoodsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Page<NideshopGoodsGalleryEntity> page = this.selectPage(
                new Query<NideshopGoodsGalleryEntity>(params).getPage(),
                new EntityWrapper<NideshopGoodsGalleryEntity>()
        );

        for (NideshopGoodsGalleryEntity gallery : page.getRecords()) {
            NideshopGoodsEntity goodsEntity = nideshopGoodsService.selectById(gallery.getGoodsId());
            if(goodsEntity!=null){
                gallery.setGoodsName(goodsEntity.getName());
            }
        }
        return new PageUtils(page);
    }

}
