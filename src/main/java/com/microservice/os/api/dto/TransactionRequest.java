package com.microservice.os.api.dto;

import com.microservice.os.api.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {
    private Order order;
    private Payment payment;
}
