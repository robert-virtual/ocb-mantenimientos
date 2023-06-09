package com.example.ocbmantenimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicResponse<T> {
    private T data;
    private String error;
    private int data_count;
    private int page;
    private String data_type;
}
