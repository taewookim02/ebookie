package com.avad.ebookie.domain.order.service;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order.dto.request.OrderCreateRequestDto;
import com.avad.ebookie.domain.order.dto.response.OrderPageDetailResponseDto;
import com.avad.ebookie.domain.order.dto.response.OrderResponseDto;
import com.avad.ebookie.domain.order.entity.Order;
import com.avad.ebookie.domain.order.mapper.OrderMapper;
import com.avad.ebookie.domain.order.repository.OrderRepository;
import com.avad.ebookie.domain.order_detail.entity.OrderDetail;
import com.avad.ebookie.domain.order_detail.repository.OrderDetailRepository;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.status.entity.Status;
import com.avad.ebookie.domain.status.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final StatusRepository statusRepository;
    private final OrderMapper orderMapper;


    @Transactional
    public OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        // get productIds
        List<Long> productIds = orderCreateRequestDto.getProductIds();

        // get member
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();


        // 새 주문 생성
        Status newOrderStatus = statusRepository.findByName("신규주문");
        Order newOrder = Order.builder()
                .member(loggedInMember)
                .status(newOrderStatus)
                .build();
        Order savedOrder = orderRepository.save(newOrder);

        // 주문 상세에 상품 넣기
        List<OrderDetail> orderDetailsToSave = new ArrayList<>();
        List<Product> productByIds = productRepository.findAllById(productIds);
        for (Product product : productByIds) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(savedOrder)
                    .product(product)
                    .quantity(1) // eBook이니까 1개만
                    .downloadCount(0)
                    .build();
            orderDetailsToSave.add(orderDetail);
        }
        orderDetailRepository.saveAll(orderDetailsToSave);

        return orderMapper.toDto(savedOrder);
    }

    public OrderPageDetailResponseDto getOrderDetails(Long orderId) {
        // get member
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        // get Order TODO: exception handle
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("order not found with the id: " + orderId));

        // 주문, 회원 검사
        boolean cannotAccess = !order.getMember().getId().equals(loggedInMember.getId());
        if (cannotAccess) {
            throw new RuntimeException("member doesn't match");
        }

        // get all orderdetails
//        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orderId);
        List<OrderDetail> orderDetails = order.getOrderDetails();
        System.out.println("orderDetails = " + orderDetails);
        return null;
    }
}
