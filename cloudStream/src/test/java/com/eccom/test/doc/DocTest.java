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
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.StringJoiner;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DocTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    private MockMvc mockMvc;

//    private WebTestClient webTestClient;

    protected final LinksSnippet pagingLinks = links(
            linkWithRel("index").optional().description("api doc index"),
            linkWithRel("api1").optional().description("api doc 1"),
            linkWithRel("api2").optional().description("api doc 2"),
            linkWithRel("api3").optional().description("api doc 3"),
            linkWithRel("api4").optional().description("api doc 4"));


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
    public void test() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andDo(document("index"
//                        ,
//                        links(linkWithRel("index").description("index"))
//                        responseFields(fieldWithPath("contact.name").description("The user's name"))
                ));
    }


    @Test
    public void testAPI1() throws Exception {
        this.mockMvc.perform(get("/api1"))
                .andExpect(status().isOk())
                .andDo(document("user/api1"
//                        links(linkWithRel("api1").description("api1"))
                ));
    }

    @Test
    public void testAPI2() throws Exception {
        this.mockMvc.perform(get("/api2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("user/api2",
//                        links(linkWithRel("api2").description("api2")),
                        responseFields(fieldWithPath("id").description("用户ID"),
                        fieldWithPath("name").description("用户名"),
                        fieldWithPath("mobileNo").description("用户手机号"))));
    }

    @Test
    public void testAPI3() throws Exception {

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api3/{name}", "helloapi"))
                .andExpect(status().isOk())
                .andDo(document("user/api3",
//                        links(linkWithRel("api3").description("api3")),
                        pathParameters(parameterWithName("name").description("用户名")
                )));
    }

    @Test
    public void testAPI4() throws Exception {
        this.mockMvc.perform(get("/api4?mobileNo=123"))
                .andExpect(status().isOk())
                .andDo(document("user/api4",
//                        links(linkWithRel("api4").description("api4")),
                        requestParameters(
                                parameterWithName("mobileNo").description("用户手机号"))));
    }

    private static <T> String getConstraints(Class<T> clazz, String property){
        ConstraintDescriptions userConstraints = new ConstraintDescriptions(clazz);
        List<String> descriptions = userConstraints.descriptionsForProperty(property);

        StringJoiner stringJoiner = new StringJoiner(". ", "", ".");
        descriptions.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }






}
