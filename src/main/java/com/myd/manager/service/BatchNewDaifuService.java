package com.myd.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myd.entity.BatchDaifu;
@Service
public interface BatchNewDaifuService {

	List<BatchDaifu> selectByDaifu(BatchDaifu batchNewDaifu);

}
