package com.pack_s.mijung_noodles.redis.application;

import com.pack_s.mijung_noodles.redis.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPubServiceImpl implements RedisPubService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void publishMessage(Message Message) {
        redisTemplate.convertAndSend("pack_channel", Message);
    }
}