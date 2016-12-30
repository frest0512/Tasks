package com.epam.task11.db;

import java.sql.Connection;

public interface TransactionOperation<T> {
    T doInTrans(Connection connection);
}
