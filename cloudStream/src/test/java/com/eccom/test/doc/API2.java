package com.eccom.test.doc;

import com.eccom.cloud.Application;
import com.eccom.cloud.model.User;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class API2 {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation).uris()
                        .withPort(8720))
                .build();
    }


    @Test
    public void testAPI5() throws Exception {

        User user = new User();
        user.setMobileNo("12361234321");
        user.setName("api5");
        Gson gson = new Gson();
        this.mockMvc.perform(post("/api5").contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andExpect(status().isOk())
                .andDo(document("post/api5",requestFields(
                        fieldWithPath("mobileNo").description("需要的用户手机号"),
                        fieldWithPath("id").description("需要的用户ID"),
                        fieldWithPath("name").description("需要的用户姓名")
                        )
                        ,responseFields(
//                                fieldWithPath("id").description("用户ID"+getConstraints(User.class,"id")),
//                                fieldWithPath("name").description("用户姓名"+getConstraints(User.class,"name")),
                                fieldWithPath("id").description("用户ID"),
                                fieldWithPath("name").description("用户姓名"),
                                fieldWithPath("mobileNo").description("手机号"))));
//                        parameterWithName("mobileNo").description("the added user"))));
    }

    @Test
    public void testAPI6() throws Exception {

        User user = new User();
        user.setMobileNo("12361234321");
        user.setName("api5");
        Gson gson = new Gson();
        final String param = "testuser";
        this.mockMvc.perform(post("/api6").contentType(MediaType.APPLICATION_JSON)
                .content(param))
                .andExpect(MockMvcResultMatchers.content().string(param))
                .andDo(document("post/api6"
                ));
//                .andDo(document("post/api6",requestParameters(
//                        parameterWithName("user").description("the added user"))));
    }
}
