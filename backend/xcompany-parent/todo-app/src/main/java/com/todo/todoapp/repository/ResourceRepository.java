package com.todo.todoapp.repository;


import com.todo.todoapp.domain.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResourceRepository extends MongoRepository<Resource, Long>, ResourceRepositoryCustom {
    Resource findByNumber(Long number);

    long deleteByNumber(Long number);
}
