package com.todo.todoapp.repository;


import com.todo.todoapp.domain.Resource;

import java.util.List;

public interface ResourceRepositoryCustom {
    Long findMaxNumber();

    Long findMinNumber();

    List<Resource> findAllByDirection(String orderType);
}
