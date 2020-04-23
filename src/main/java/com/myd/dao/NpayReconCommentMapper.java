package com.myd.dao;

import com.myd.entity.NpayReconComment;

public interface NpayReconCommentMapper {
    int insert(NpayReconComment record);

    int insertSelective(NpayReconComment record);
}