package com.yiyuan.service.impl;

import com.yiyuan.entity.VerificationCode;
import com.yiyuan.service.VerificationCodeService;
import com.yiyuan.vo.EmailVo;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Override
    public EmailVo sendEmail(VerificationCode code) {
        return null;
    }

    @Override
    public void validated(VerificationCode code) {

    }
}
