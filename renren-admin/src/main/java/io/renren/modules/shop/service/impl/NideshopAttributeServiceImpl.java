package io.renren.modules.shop.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.NideshopAttributeDao;
import io.renren.modules.shop.entity.NideshopAttributeEntity;
import io.renren.modules.shop.service.NideshopAttributeService;


@Service("nideshopAttributeService")
public class NideshopAttributeServiceImpl extends ServiceImpl<NideshopAttributeDao, NideshopAttributeEntity> implements NideshopAttributeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NideshopAttributeEntity> page = this.selectPage(
                new Query<NideshopAttributeEntity>(params).getPage(),
                new EntityWrapper<NideshopAttributeEntity>()
        );

        return new PageUtils(page);
    }

}
