package com.test;

import com.qf.mapper.HouseInfoMapper;
import com.qf.pojo.HouseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMapper {
    @Resource
    private HouseInfoMapper houseInfoMapper;

    @Test
    public void test(){
        HouseInfo info=new HouseInfo();
        info.setTitle("蘑菇屋");
        info.setPrice(7800);
        info.setImages("http://192.168.44.73:82/group1/M00/00/00/wKgsSV02sHCANrUqAALfgAAll0Y858.jpg");
        info.setAddress("北京");
        info.setDesc("一居室");

        int i = houseInfoMapper.saveHouseInfo(info);
        System.out.println(i>0?"success":"fail");

    }
}
