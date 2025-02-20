package com.avad.ebookie.domain.payment.service;

import com.avad.ebookie.client.PortOneApiClient;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order.entity.Order;
import com.avad.ebookie.domain.order.repository.OrderRepository;
import com.avad.ebookie.domain.order_detail.entity.OrderDetail;
import com.avad.ebookie.domain.payment.dto.PaymentCompleteResponseDto;
import com.avad.ebookie.domain.payment.dto.request.PaymentCompleteRequestDto;
import com.avad.ebookie.domain.payment.dto.request.PaymentCreateRequestDto;
import com.avad.ebookie.domain.payment.dto.response.PaymentResponseDto;
import com.avad.ebookie.domain.payment.entity.Payment;
import com.avad.ebookie.domain.payment.entity.PaymentStatus;
import com.avad.ebookie.domain.payment.mapper.PaymentMapper;
import com.avad.ebookie.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;
    private final PortOneApiClient portOneApiClient;

    @Value("${application.port-one.secret-key}")
    private String apiSecretKey;


    @Transactional
    public PaymentResponseDto createPayment(PaymentCreateRequestDto requestDto) {
        // get member
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        // get order
        Order order = orderRepository.findById(requestDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));

        boolean isNotOrderOwner = !order.getMember().getId().equals(loggedInMember.getId());
        if (isNotOrderOwner) {
            throw new RuntimeException("권한이 없습니다");
        }

        // build Payment
        Payment payment = Payment.builder()
                .id(requestDto.getPaymentId())
                .member(loggedInMember)
                .paymentStatus(requestDto.getPaymentStatus())
                .paidPrice(requestDto.getPaidPrice())
                .member(loggedInMember)
                .order(order)
                .build();
        Payment savedPayment = paymentRepository.save(payment);

        // save payment
        return paymentMapper.toDto(savedPayment);
        // make dto

        // return
    }

    @Transactional
    public PaymentCompleteResponseDto completePayment(PaymentCompleteRequestDto requestDto) {
        Map<String, Object> paymentDetails = portOneApiClient.getPaymentDetails(requestDto.getPaymentId());

        // 포트원 단건조회 api 정보
        String status = (String) paymentDetails.get("status");
        String paymentId = (String) paymentDetails.get("id");
        Integer paid = (Integer) ((LinkedHashMap) paymentDetails.get("amount")).get("paid");

        // 결제 정보 가져오기
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // 주문 정보에서 총액 가져오기
        Order order = payment.getOrder();
        double expectedTotal = order.getTotalPrice();

        if (!"PAID".equals(status)) {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            throw new RuntimeException("Payment not completed successfully");
        }

        double tolerance = 0.01;
        if (Math.abs(expectedTotal - paid) > tolerance) {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            throw new RuntimeException("Payment amount mismatch. Expected: " + expectedTotal + ", Paid: " + paid);
        }

        // 검사 완료,
        payment.setPaymentStatus(PaymentStatus.PAID);
        Payment savedPayment = paymentRepository.save(payment);
        // TODO: 주문 상태 변겅
        // order.setStatus();

        return PaymentCompleteResponseDto.builder()
                .paymentId(savedPayment.getId())
                .paymentStatus(savedPayment.getPaymentStatus())
                .build();
    }
}
