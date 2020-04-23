package com.myd.serviceimpl;

import com.myd.dao.TestMapper;
import com.myd.entity.TestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by you on 2018/11/21.
 */

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public TestModel getData(TestModel model) {

        return testMapper.getData(model);

    }

    public List<TestModel> llist() {
        return testMapper.getAllUser();
    }
}
