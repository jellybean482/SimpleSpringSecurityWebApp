package com.jia.security.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.jia.security.config.SecurityConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SecurityConfig.class })
@WebAppConfiguration
public class LoginUnitTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void givenValidRequestWithValidUserCredentials_shouldLoginSuccessfully() throws Exception {
        mvc.perform(get("/user").with(httpBasic("user", "password"))).andExpect(authenticated());
    }
    
    @Test
    public void givenValidRequestWithValidAdminCredentials_shouldLoginSuccessfully() throws Exception {
        mvc.perform(get("/admin").with(httpBasic("admin", "password"))).andExpect(authenticated());
    }

    @Test
    public void givenValidRequestWithInvalidUserCredentials() throws Exception {
        mvc.perform(get("/user").with(httpBasic("user", "wrong"))).andExpect(unauthenticated());
    }
    
    @Test
    public void givenValidRequestWithInvalidAdminCredentials() throws Exception {
        mvc.perform(get("/admin").with(httpBasic("admin", "wrong"))).andExpect(unauthenticated());
    }
    
    @Test
    public void givenValidRequestWithAnonymous() throws Exception {
        mvc.perform(get("/").with(anonymous())).andExpect(authenticated());
    }
    
    @Test
    public void givenValidRequestWithInvalidAnonymous() throws Exception {
        mvc.perform(get("/user").with(anonymous())).andExpect(unauthenticated());
    }
}
