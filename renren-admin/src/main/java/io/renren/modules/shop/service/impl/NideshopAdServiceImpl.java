package io.renren.modules.shop.service.impl;

import io.renren.common.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

        for(NideshopAdEntity en:page.getRecords()){
            if(en.getStartTime()!=null) {
                long millions=new Long(en.getStartTime()).longValue()*1000;
                Date date=new Date(millions);
                en.setAdStartTime(DateUtils.format(date, "yyyy-MM-dd"));
                millions=new Long(en.getEndTime()).longValue()*1000;
                date=new Date(millions);
                en.setAdEndTime(DateUtils.format(date, "yyyy-MM-dd"));
            }
        }
        return new PageUtils(page);
    }

}
