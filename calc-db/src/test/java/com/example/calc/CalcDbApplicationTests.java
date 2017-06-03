package com.example.calc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CalcDbApplicationTests {
  @Autowired
  private MockMvc mock;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void restTest() throws Exception {
    final String op1 = "3*7.5+2";
    final CalcEntry entry1 = new CalcEntry(op1);

    final String op2 = "SQRT(25)";
    final CalcEntry entry2 = new CalcEntry(op2);

    final String op3 = "random()";
    final CalcEntry entry3 = new CalcEntry(op3);

    // insert two values
    mock.perform(post("/history").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(objectMapper.writeValueAsString(entry1))).andExpect(status().isCreated());

    mock.perform(post("/history").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(objectMapper.writeValueAsString(entry2))).andExpect(status().isCreated());

    mock.perform(post("/history").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(objectMapper.writeValueAsString(entry3))).andExpect(status().isCreated());

    // get all values and count size
    mock.perform(get("/histories").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(3)));

    // check values with calculated value
    mock.perform(get("/history/1").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.operation").value(op1)).andExpect(jsonPath("$.result").value("24.5"))
        .andExpect(jsonPath("$.valid").value("true"))
        .andExpect(jsonPath("$.date").value(entry1.getDate().getTime()));

    mock.perform(get("/history/2").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk()).andExpect(jsonPath("$.result").value("5.0"));

    mock.perform(get("/history/3").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk()).andExpect(jsonPath("$.operation").value(op3));

    // value not found
    mock.perform(get("/history/99").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isNotFound());
  }
}
