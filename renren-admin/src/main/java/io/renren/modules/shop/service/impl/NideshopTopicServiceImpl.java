package io.renren.modules.shop.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.NideshopTopicDao;
import io.renren.modules.shop.entity.NideshopTopicEntity;
import io.renren.modules.shop.service.NideshopTopicService;


@Service("nideshopTopicService")
public class NideshopTopicServiceImpl extends ServiceImpl<NideshopTopicDao, NideshopTopicEntity> implements NideshopTopicService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NideshopTopicEntity> page = this.selectPage(
                new Query<NideshopTopicEntity>(params).getPage(),
                new EntityWrapper<NideshopTopicEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<NideshopTopicEntity> queryTopicList(String topicName) {
        return baseMapper.queryTopicList(topicName);
    }
}
