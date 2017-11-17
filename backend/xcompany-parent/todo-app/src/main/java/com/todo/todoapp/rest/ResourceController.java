package com.todo.todoapp.rest;


import com.todo.todoapp.domain.Resource;
import com.todo.todoapp.exception.DuplicateResourceFound;
import com.todo.todoapp.exception.ResourceNotFoundException;
import com.todo.todoapp.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/resource")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceRepository resourceRepository;

    @GetMapping(value = "")
    public List<Resource> all(@RequestParam(value = "type",required = false) String type) throws DuplicateResourceFound {
            return resourceRepository.findAllByDirection(type);
    }

    @PostMapping(value = "/save")
    public String save(@RequestBody Resource numbers) throws DuplicateResourceFound {
        Resource resource;
        try {
            resource = resourceRepository.save(numbers);
        } catch (DuplicateKeyException e) {
            throw new DuplicateResourceFound(500, e.getMessage());
        }
        return String.format("%d number saved successfully", resource.getNumber());
    }

    @GetMapping(value = "/find/{number}")
    public @ResponseBody
    Resource findByNumber(@PathVariable(name = "number") Long number) throws ResourceNotFoundException {
        Resource resource = resourceRepository.findByNumber(number);
        if (Objects.isNull(resource)) {
            throw new ResourceNotFoundException(500, "Resource not found..");
        }
        return resource;
    }

    @GetMapping(value = "/max")
    public Long findMaxNumber() {
        return resourceRepository.findMaxNumber();
    }

    @GetMapping(value = "/min")
    public Long findMinNumber() {
        return resourceRepository.findMinNumber();
    }

    @DeleteMapping(value = "/delete/{number}")
    public void deleteById(@PathVariable(value = "number") Long number) throws ResourceNotFoundException {
        findByNumber(number);
        resourceRepository.deleteByNumber(number);
    }
}
