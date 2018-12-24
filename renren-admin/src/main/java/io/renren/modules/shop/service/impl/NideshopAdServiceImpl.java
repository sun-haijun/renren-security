package io.renren.modules.shop.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.NideshopAdDao;
import io.renren.modules.shop.entity.NideshopAdEntity;
import io.renren.modules.shop.service.NideshopAdService;


@Service("nideshopAdService")
public class NideshopAdServiceImpl extends ServiceImpl<NideshopAdDao, NideshopAdEntity> implements NideshopAdService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NideshopAdEntity> page = this.selectPage(
                new Query<NideshopAdEntity>(params).getPage(),
                new EntityWrapper<NideshopAdEntity>()
        );

        return new PageUtils(page);
    }

}
