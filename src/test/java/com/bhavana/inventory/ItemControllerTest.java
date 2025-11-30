

package com.bhavana.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
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

        // Create the item and capture the Location header with its server-generated id
        MvcResult result = mvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/items/")))
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        String id = location.substring(location.lastIndexOf('/') + 1);

        // Verify listing works
        mvc.perform(get("/api/items"))
                .andExpect(status().isOk());

        // Adjust quantity using the actual id (no hardcoded 1)
        mvc.perform(patch("/api/items/" + id + "/adjust?delta=-2"))
                .andExpect(status().isOk());
    }
}

