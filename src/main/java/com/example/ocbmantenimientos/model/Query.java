package com.example.ocbmantenimientos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Query {
    public static final int ACTION_INSERT = 1;
    public static final int ACTION_UPDATE = 2;
    public static final int ACTION_DELETE = 3;
    private long id;
    private Table table;
    private Action action;
    private String parameters;
    private String whereCondition;
    private String response;
    private String status;
    private User requestedBy;
    private User authorizedBy;
    private LocalDateTime requestedAt;
    private LocalDateTime authorizedAt;
    public static final String STATUS_REQUESTED = "requested";
    public static final String STATUS_AUTHORIZED = "authorized";
}
