package com.example.entity;

import com.example.entity.utils.codeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class EntityApplicationTests {

    @Test
    void contextLoads() {
        String token = UUID.randomUUID().toString();
        System.out.println(
                token
        );
      String valueCode= codeUtils.getPhoneCodeValue(6);
        System.out.println(valueCode);
    }

}
