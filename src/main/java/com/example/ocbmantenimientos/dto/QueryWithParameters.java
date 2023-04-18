package com.example.ocbmantenimientos.dto;

import lombok.Data;


@Data
public class QueryWithParameters {
    String query;
    Object[] values;
}
