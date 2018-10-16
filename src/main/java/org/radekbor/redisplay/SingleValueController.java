package org.radekbor.redisplay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("value")
public class SingleValueController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/{key}/{value}")
    public void addValue(@PathVariable("key") String key,
                    @PathVariable("value") String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("/{key}")
    public String getValue(@PathVariable("key") String key) {
        return redisTemplate.opsForValue().get(key).toString();
    }
}
