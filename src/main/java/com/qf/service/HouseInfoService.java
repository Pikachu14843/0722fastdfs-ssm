package com.qf.service;

import com.qf.pojo.HouseInfo;

import java.util.List;

public interface HouseInfoService {
    public int saveHouseInfo(HouseInfo houseInfo);

    public List<HouseInfo> findAll();
}
