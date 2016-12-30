package com.epam.task11.dao.api;

import com.epam.task11.entity.db.Payment;

public interface PaymentDAO {
    long add(Payment payment);
}
