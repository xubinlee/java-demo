package com.ex.estest;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ESController {

    @Autowired
    private TransportClient transportClient;

    @GetMapping("/book")
    public ResponseEntity getBook(@RequestParam String id){
        GetResponse response = transportClient.prepareGet("book", "novel", id).get();
        return new ResponseEntity(response.getSource(), HttpStatus.OK);
    }
}
