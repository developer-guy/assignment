package com.todo.todoapp.repository;


import com.netflix.discovery.converters.Auto;
import com.todo.todoapp.domain.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ResourceRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ResourceRepository resourceRepository;


    @Test
    public void should_find_max_number(){
        Resource resource = new Resource();
        resource.setNumber(1);
        Resource resource2 = new Resource();
        resource.setNumber(2);
        Resource resource3 = new Resource();
        resource.setNumber(3);

        testEntityManager.persist(resource);
        testEntityManager.persist(resource2);
        testEntityManager.persist(resource3);


        Long maxNumber = resourceRepository.findMaxNumber();
        assertThat(maxNumber,equalTo(3L));
    }
}
