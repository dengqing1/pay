package com.myd.dao;

import org.springframework.stereotype.Repository;

import com.myd.entity.TestModel;

import java.util.List;

/**
 * Created by you on 2018/11/15.
 */


@Repository
public interface TestMapper {

    TestModel getData(TestModel model);

    List<TestModel> getAllUser();
}
