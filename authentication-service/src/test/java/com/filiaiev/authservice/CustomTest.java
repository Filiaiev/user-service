package com.filiaiev.authservice;

import com.filiaiev.authservice.repository.entity.UserRoleDO;
import org.junit.jupiter.api.Test;

public class CustomTest {

    @Test
    void test() {
        UserRoleDO admin = UserRoleDO.forValue("admin");
        System.out.println(admin);
    }
}
