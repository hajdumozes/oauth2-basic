package poc.oauth2.basic.web;

import poc.oauth2.basic.config.WebConfig;
import jakarta.servlet.Filter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {WebConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
class IndexControllerIntegrationTest {
    @Autowired
    IndexController controller;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    SecurityFilterChain filterChain;

    MockMvc mockMvc;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(springSecurity())
                .addFilters(filterChain.getFilters().toArray(Filter[]::new))
                .build();
    }

    @Test
    void shouldBeAuthenticated_whenCallingHello_givenOpaqueToken() throws Exception {
        // when
        String key = "sub";
        String username = "mozes";
        String output = mockMvc
                .perform(get("/hello")
                        .with(SecurityMockMvcRequestPostProcessors.opaqueToken()
                                .attributes(attr -> attr.put(key, username))))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        Assertions.assertEquals(username + " says hello", output);
    }
}