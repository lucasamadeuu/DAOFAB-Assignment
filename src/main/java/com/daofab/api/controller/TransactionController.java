package com.daofab.api.controller;

import com.daofab.api.model.ParentRepository;
import com.daofab.api.response.ChildResponse;
import com.daofab.api.response.ParentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/")
public class TransactionController {

    @Autowired
    private ParentRepository parentRepository;

    @GetMapping("parent-transactions")
    public ResponseEntity<Set<ParentResponse>> getParentTransactions(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if(page == null)
            page = 0; //Default page number

        if(size == null)
            size = 2; //Default page size

        //Responsible for getting the Parent data with page and size filter
        Set<ParentResponse> parentTransactions = parentRepository.getParentTransactions(page, size);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(parentTransactions);
    }

    @GetMapping("/child-transactions/{parentId}")
    public ResponseEntity<Set<ChildResponse>> getChildTransactions(@PathVariable("parentId") long parentId){
        Set<ChildResponse> childTransactions = parentRepository.getChildTransactions(parentId);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(childTransactions);
    }

}
