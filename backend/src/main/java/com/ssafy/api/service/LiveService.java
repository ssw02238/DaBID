package com.ssafy.api.service;

import com.ssafy.api.request.LiveRegisterPostReq;
import com.ssafy.db.entity.Live;

import java.util.List;

/**
 *	라이브 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface LiveService {
    void createLive(String userId, LiveRegisterPostReq liveRegisterPostReq);
    void updateLive(LiveRegisterPostReq liveRegisterPostReq, Live live);
    Live getLiveByPrdId(int prdId);
    void deleteLive(int prdId);
    List<Live> getAllLives();
}
