package com.todo.todoapp.repository.impl;


import com.todo.todoapp.domain.Resource;
import com.todo.todoapp.repository.ResourceRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ResourceRepositoryImpl implements ResourceRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public List<Resource> findAllByDirection(String orderType) {
        final Query query = new Query()
                .with(new Sort(direction(orderType), "number"));
        return mongoTemplate.find(query,Resource.class);
    }

    private Sort.Direction direction(String orderType) {
        Sort.Direction direction = Sort.Direction.fromStringOrNull(orderType);
        if (Objects.isNull(direction)) {
            return Sort.Direction.DESC;
        }
        return direction;
    }


    @Override
    public Long findMaxNumber() {
        return findNumber(Sort.Direction.DESC);
    }

    @Override
    public Long findMinNumber() {
        return findNumber(Sort.Direction.ASC);
    }

    private Long findNumber(Sort.Direction direction) {
        final Query query = new Query()
                .limit(1)
                .with(new Sort(direction, "insertDate"));
        Resource resource = mongoTemplate.findOne(query, Resource.class);
        if (Objects.isNull(resource)) {
            return -1L;
        }
        return resource.getNumber();
    }

}
