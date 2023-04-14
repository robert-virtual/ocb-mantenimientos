package com.example.ocbmantenimientos.service;

import com.example.ocbmantenimientos.model.Query;
import com.example.ocbmantenimientos.model.User;
import com.example.ocbmantenimientos.repository.QueryRepository;
import com.example.ocbmantenimientos.service.JwtService;
import com.example.ocbmantenimientos.utils.QueryStatus;
import com.example.ocbmantenimientos.utils.QueryUtils;
import com.example.ocbmantenimientos.utils.RoleValues;
import com.example.ocbmantenimientos.utils.UserUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorizeService {

    private final JdbcTemplate jdbcTemplate;
    private final QueryRepository queryRepository;
    private final JwtService jwtService;

    public Query authorize(String authorization, long query_id) throws Exception {
        Optional<Query> optionalQuery = queryRepository.findById(query_id);
        Query query = QueryUtils.validateQuery(optionalQuery);
        User user = jwtService.getUser(authorization);
        UserUtils.checkUserRole(user, RoleValues.QUERY_AUTHORIZER);
        String response = "";
        int affectedRows = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parameters = objectMapper.readValue(query.getParameters(), new TypeReference<>() {
        });
        Map<String, Object> where = objectMapper.readValue(query.getWhereCondition(), new TypeReference<>() {
        });
        Object id = where.get(where.keySet().toArray()[0]) == null ? 0 : parameters.get("id");
        parameters.remove("id");
        String fieldsString = String.join(",", parameters.keySet());//field1,field2
        List<String> fields = parameters.keySet().stream().map(k -> String.format("%s=?", k)).collect(Collectors.toList());//field1=?,field2=?
        String valuesPlaceholders = String.join(",", Collections.nCopies(parameters.size(), "?")); // ?,?
        switch (query.getAction().getId()) {
            case Query.ACTION_INSERT:
                affectedRows = jdbcTemplate.update(
                        String.format(
                                "insert into %s(%s) values(%s)",
                                query.getTable().getName(),
                                fieldsString,
                                valuesPlaceholders
                        ),
                        parameters.values()
                );
                break;
            case Query.ACTION_UPDATE:
                affectedRows = jdbcTemplate.update(
                        String.format(
                                "update %s set %s where id = ?",
                                query.getTable().getName(),
                                String.join(",", fields)
                        ),
                        parameters.values(),
                        id

                );
                break;
            case Query.ACTION_DELETE:
                affectedRows = jdbcTemplate.update(
                        String.format(
                                "delete from %s where id = ?",
                                query.getTable().getName()
                        ),
                        id
                );
                break;
        }
        query.setStatus(QueryStatus.STATUS_AUTHORIZED.toString());
        query.setAuthorizedAt(LocalDateTime.now());
        query.setResponse(response);
        query.setAuthorizedBy(user);
        return queryRepository.save(query);
    }


}
