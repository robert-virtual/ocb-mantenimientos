package com.example.ocbmantenimientos.controller;

import com.example.ocbmantenimientos.dto.BasicResponse;
import com.example.ocbmantenimientos.model.Query;
import com.example.ocbmantenimientos.service.AuthorizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
// business logic:
// service logic:

// TODO: separate business and service logic
@RestController
@RequiredArgsConstructor
public class Controller {
    private final AuthorizeService authorizeService;

    @PostMapping("/authorize/{query_id}")
    public ResponseEntity<BasicResponse<Query>> authorize(
            @RequestHeader("Authorization") String authorization,
            @PathVariable long query_id
    ) {
        try {
            return ResponseEntity.ok(
                    BasicResponse.<Query>builder()
                            .data(authorizeService.authorize(authorization, query_id))
                            .build()
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    BasicResponse.<Query>builder().error(e.getMessage()).build(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
