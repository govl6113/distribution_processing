package com.pack_s.coffee.redis.application;

import com.pack_s.coffee.redis.dto.Message;

public interface RedisPubService {

    void publishMessage(Message Message);
}
