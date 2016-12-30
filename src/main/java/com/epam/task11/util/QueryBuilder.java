package com.epam.task11.util;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private StringBuilder query = new StringBuilder();
    private List<Object> params = new ArrayList<>();

    public List<Object> params() {
        return params;
    }

    public QueryBuilder selectFrom(String table) {
        query.append(" SELECT * FROM " + table);
        return this;
    }

    private void putWhere() {
        if (params.size() == 0) {
            query.append(" where ");
        }
    }

    public QueryBuilder like(String key, String value) {
        if (value == null) {
            return this;
        }
        putWhere();
        if (params.size() != 0) {
            and();
        }
        query.append(key).append(" like ? ");
        params.add(value);
        return this;
    }

    public QueryBuilder in(String key, List<String> value) {
        if (value == null) {
            return this;
        }
        putWhere();
        if (params.size() != 0) {
            and();
        }
        query.append(key + " in(");
        for (int i = 0; i < value.size() - 1; i++) {
            query.append("?,");
            params.add(value.get(i));
        }
        if (value.size() > 0) {
            query.append("?");
            params.add(value.get(value.size() - 1));

        }
        query.append(") ");
        return this;
    }

    public QueryBuilder between(String fieldName, String priceFrom, String priceTo) {
        if (priceFrom == null && priceTo == null) {
            return this;
        }
        putWhere();
        if (params.size() != 0) {
            and();
        }
        query.append(" ").append(fieldName).append(" between ? and ? ");
        params.add(priceFrom);
        params.add(priceTo);
        return this;
    }

    public QueryBuilder order(String fieldName) {
        query.append(" order by ").append(fieldName).append(" ");
        return this;
    }

    public QueryBuilder limit(int begine, int end) {
        query.append(" limit ?,? ");
        params.add(begine);
        params.add(end);
        return this;
    }

    public QueryBuilder and() {
        query.append(" and ");
        return this;
    }

    public QueryBuilder or() {
        query.append(" or ");
        return this;
    }

    public String fetch() {
        return query.toString();
    }

    public String nameInTheMiddle(String name) {
        if (name != null) {
            return "%" + name + "%";
        }
        return null;
    }
}
