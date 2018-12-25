package io.renren.modules.shop.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.NideshopBrandDao;
import io.renren.modules.shop.entity.NideshopBrandEntity;
import io.renren.modules.shop.service.NideshopBrandService;


@Service("nideshopBrandService")
public class NideshopBrandServiceImpl extends ServiceImpl<NideshopBrandDao, NideshopBrandEntity> implements NideshopBrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NideshopBrandEntity> page = this.selectPage(
                new Query<NideshopBrandEntity>(params).getPage(),
                new EntityWrapper<NideshopBrandEntity>()
        );

        return new PageUtils(page);
    }

}
