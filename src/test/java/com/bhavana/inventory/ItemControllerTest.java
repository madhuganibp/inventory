
package com.bhavana.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {
    @Autowired MockMvc mvc;

    @Test
    void createListAdjust() throws Exception {
        String json = """
          {"sku":"SKU-1001","name":"Keyboard","quantity":10,"price":29.99}
        """;
        mvc.perform(post("/api/items").contentType(MediaType.APPLICATION_JSON).content(json))
           .andExpect(status().isCreated())
           .andExpect(header().string("Location", org.hamcrest.Matchers.containsString("/api/items/")));

        mvc.perform(get("/api/items")).andExpect(status().isOk());

        mvc.perform(patch("/api/items/1/adjust?delta=-2")).andExpect(status().isOk());
    }
}
