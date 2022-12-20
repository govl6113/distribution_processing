package com.pack_s.mijung_noodles.redis.application;

import com.pack_s.mijung_noodles.redis.dto.Message;

public interface RedisPubService {

    void publishMessage(Message Message);
}
