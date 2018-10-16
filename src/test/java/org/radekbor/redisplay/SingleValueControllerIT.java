package org.radekbor.redisplay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("it")
public class SingleValueControllerIT {


    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @Autowired
    private SingleValueController singleValueController;

    @Before
    public void startRedis() throws IOException {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @After
    public void stopRedis() {
        redisServer.stop();
    }


    @Test
    public void shouldBeAbleToAddItemAndGet() {
        // Given
        String key = "KEY 1";
        String value = "Value 1";

        // When
        singleValueController.addValue(key, value);
        String result = singleValueController.getValue(key);

        assertThat(result).isEqualTo(value);
    }

}
