package com.microservice.os.api.service;

import com.microservice.os.api.dto.Payment;
import com.microservice.os.api.dto.TransactionRequest;
import com.microservice.os.api.dto.TransactionResponse;
import com.microservice.os.api.entity.Order;
import com.microservice.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate  template;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
       Payment paymentResponse= template.postForObject("http://PAYMENT-SERVICE/payment/",payment, Payment.class);
        response =paymentResponse.getPaymentStatus().equals("success")?"payment success and order got placed":"there is a faliure in payment api";


       return  new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }

    public List<Order> allOrders(){
       return orderRepository.findAll();
    }


}
