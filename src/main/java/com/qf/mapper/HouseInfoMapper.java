package com.qf.mapper;

import com.qf.pojo.HouseInfo;

import java.util.List;

public interface HouseInfoMapper {
    public int saveHouseInfo(HouseInfo houseInfo);

    public List<HouseInfo> findAll();
}
