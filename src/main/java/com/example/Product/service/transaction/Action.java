package com.example.Product.service.transaction;

import com.arangodb.ArangoDatabase;

public interface Action<T> {
    T perform(ArangoDatabase arangoDatabase, String transactionId);
}
