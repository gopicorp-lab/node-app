package com.example.demo.unit;


import com.example.demo.controller.HelloController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class HelloControllerUnitTest {


    @Test
    void helloMessageTest(){


        HelloController controller =
                new HelloController();


        String response =
                controller.hello();


        assertEquals(
            "Spring Boot Application Running",
            response
        );

    }

}