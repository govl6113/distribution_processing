package com.pack_s.hongkong_banjeom.redis.application;

import com.pack_s.hongkong_banjeom.redis.dto.Message;

public interface RedisPubService {

    void publishMessage(Message Message);
}
