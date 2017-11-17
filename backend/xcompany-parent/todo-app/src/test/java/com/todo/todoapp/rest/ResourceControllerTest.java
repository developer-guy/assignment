package com.todo.todoapp.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.todoapp.domain.Resource;
import com.todo.todoapp.repository.ResourceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ResourceController.class)
@RunWith(SpringRunner.class)
public class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResourceRepository resourceRepository;


    private ObjectMapper objectMapper;

    @Before
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void should_return_single_list() throws Exception {
        //GIVEN
        Resource resource = new Resource();
        resource.setInsertDate(new Date());
        resource.setNumber(1);
        List<Resource> resources = Arrays.asList(resource);
        when(resourceRepository.findAllByDirection(any())).thenReturn(resources);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/resource/")).
                andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
                andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    public void should_save_resource() throws Exception {
        //GIVEN
        Resource resource = new Resource();
        resource.setInsertDate(new Date());
        resource.setNumber(1);
        when(resourceRepository.save(any(Resource.class))).thenReturn(resource);
        String content = objectMapper.writeValueAsString(resource);
        //WHEN
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/resource/save/").contentType(MediaType.APPLICATION_JSON).content(content)).
                andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn().getResponse();

        String body = response.getContentAsString();

        assertThat(body, equalTo("1'number saved successfully"));
    }

    @Test
    public void should_throw_duplicateresource_exception_when_resource_has_same_number() throws Exception {
        //GIVEN
        Resource resource = new Resource();
        resource.setInsertDate(new Date());
        resource.setNumber(1);
        String content = objectMapper.writeValueAsString(resource);
        when(resourceRepository.save(any(Resource.class))).thenThrow(DuplicateKeyException.class);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/resource/save/").
                contentType(MediaType.APPLICATION_JSON).content(content)).
                andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }


    @Test
    public void should_return_max_number() throws Exception {
        //GIVEN
        when(resourceRepository.findMaxNumber()).thenReturn(1l);
        //WHEN
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/resource/max")).
                andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn().getResponse();
        String content = response.getContentAsString();
        assertThat(content, equalTo("1"));
    }


    @Test
    public void should_return_min_number() throws Exception {
        //GIVEN
        when(resourceRepository.findMinNumber()).thenReturn(1l);
        //WHEN
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/resource/min")).
                andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn().getResponse();
        String content = response.getContentAsString();
        assertThat(content, equalTo("1"));
    }

    @Test
    public void should_return_number() throws Exception {
        //GIVEN
        when(resourceRepository.findByNumber(anyLong())).thenReturn(new Resource());
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/resource/find/{number}", 1l)).
                andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void should_throw_resourcenotfound_exception_when_no_resource_found() throws Exception {
        //GIVEN
        when(resourceRepository.findByNumber(anyLong())).thenReturn(null);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/resource/find/{number}", 1l)).
                andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}
