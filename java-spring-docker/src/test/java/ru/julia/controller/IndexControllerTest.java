package ru.julia.controller;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.julia.BasePersistenceTest;

import java.net.InetAddress;

@AutoConfigureMockMvc
class IndexControllerTest extends BasePersistenceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void jdbcDemo() throws Exception {
        var result = mockMvc
                .perform(get("/jdbcDemo"))
                .andReturn()
                .getResponse();
        assertThat(result.getStatus()).isEqualTo(SC_OK);
        assertThat(result.getContentAsString()).isEqualTo("1");
    }

    @Test
    void hi() throws Exception {
        String name = "Julia";
        String expected = String.format("Hi, %s. It works, host: %s",
                name,
                InetAddress.getLocalHost().getHostName());

        var result = mockMvc
                .perform(get("/hi").param("name", name))
                .andReturn()
                .getResponse();

        assertThat(result.getContentAsString()).isEqualTo(expected);
    }

    @Test
    void response() throws Exception {
        String name = "Julia", param1 = "value1", param2 = "value2";

        String content = String.format("{\"param1\":\"%s\",\"param2\":\"%s\"}", param1, param2);
        String expected = String.format("{\"name\":\"%s\",\"result\":\"%s-%s\"}", name, param1, param2);

        var result = mockMvc
                .perform(post("/response/{name}", name)
                                .header("content-type", "application/json")
                                .content(content))
                .andReturn()
                .getResponse();

        assertThat(result.getContentAsString()).isEqualTo(expected);
    }
}