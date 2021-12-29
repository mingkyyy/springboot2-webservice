package com.mingkyy.book.springboot.web;

import com.mingkyy.book.springboot.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = SecurityConfig.class)
})
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc; // 빈 생성해줌

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) //에러 안 뜸?
                .andExpect(content().string(hello)); //hello로 리턴 잘 됨?
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))) //string 형 밖에 못함 무조건 string 형으로 변경해서 해야 함.
                .andExpect(status().isOk()) // 200 (=에러 없음) 됨???
                .andExpect(jsonPath("$.name", is(name))) // 생성자 잘 생성해서 생성자 값이랑 맞니?
                .andExpect(jsonPath("$.amount", is(amount)));


    }


}
