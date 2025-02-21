package com.avad.ebookie.domain.order.service;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order.dto.request.OrderCreateRequestDto;
import com.avad.ebookie.domain.order.dto.response.OrderPageDetailResponseDto;
import com.avad.ebookie.domain.order.dto.response.OrderPageResponseDto;
import com.avad.ebookie.domain.order.dto.response.OrderResponseDto;
import com.avad.ebookie.domain.order.entity.Order;
import com.avad.ebookie.domain.order.mapper.OrderMapper;
import com.avad.ebookie.domain.order.repository.OrderRepository;
import com.avad.ebookie.domain.order_detail.dto.response.OrderDetailResponseDto;
import com.avad.ebookie.domain.order_detail.entity.OrderDetail;
import com.avad.ebookie.domain.order_detail.mapper.OrderDetailMapper;
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
    private final OrderDetailMapper orderDetailMapper;


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
        
        // 할인율 적용한 가격 계산
        List<Product> productByIds = productRepository.findAllById(productIds);
        Double totalPrice = productByIds.stream()
                .mapToDouble(product -> {
                    Double price = product.getPrice();
                    Double discountRatePercentage = product.getDiscountRate();
                    return price - (price * discountRatePercentage / 100);
                })
                .sum();
        newOrder.setTotalPrice(totalPrice);
        
        Order savedOrder = orderRepository.save(newOrder);

        // 주문 상세에 상품 넣기
        List<OrderDetail> orderDetailsToSave = new ArrayList<>();
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

    @Transactional(readOnly = true)
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
        List<OrderDetail> orderDetails = order.getOrderDetails();
        List<OrderDetailResponseDto> orderDetailDtos = orderDetailMapper.toDtoList(orderDetails);


        return OrderPageDetailResponseDto.builder()
                .orderDetailDtos(orderDetailDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public OrderPageResponseDto getListOfOrders() {
        // get member
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        // get Order
        List<Order> orders = loggedInMember.getOrders();

        // get order details
//        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderIn(orders);

//        System.out.println("orders = " + orders);
        System.out.println("hello world");
        return null;
    }
}
