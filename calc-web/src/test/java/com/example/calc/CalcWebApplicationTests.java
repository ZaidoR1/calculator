package com.example.calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalcWebApplicationTests {
  @Test
  public void contextLoads() {
    Assert.isTrue(true, "Something went wrong :(");
  }
}
