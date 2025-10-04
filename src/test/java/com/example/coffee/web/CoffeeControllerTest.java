package com.example.coffee.web;

import com.example.coffee.CoffeeApiApplication;
import com.example.coffee.model.Coffee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CoffeeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CoffeeControllerTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    @Test void health_ok() throws Exception {
        mvc.perform(get("/api/coffee/health")).andExpect(status().isOk()).andExpect(content().string("OK"));
    }
    @Test void all_ok() throws Exception {
        mvc.perform(get("/api/coffee")).andExpect(status().isOk());
    }
    @Test void one_found() throws Exception {
        mvc.perform(get("/api/coffee/1")).andExpect(status().isOk());
    }
    @Test void one_notFound() throws Exception {
        mvc.perform(get("/api/coffee/999")).andExpect(status().isNotFound());
    }
    @Test void add_ok() throws Exception {
        Coffee c = new Coffee(10,"Flat White",420);
        mvc.perform(post("/api/coffee")
           .contentType(MediaType.APPLICATION_JSON)
           .content(om.writeValueAsString(c)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("Flat White"));
    }
    @Test void total_ok() throws Exception {
        mvc.perform(post("/api/coffee/total")
          .contentType(MediaType.APPLICATION_JSON)
          .content(om.writeValueAsString(java.util.List.of(1,2,3))))
          .andExpect(status().isOk());
    }
}
