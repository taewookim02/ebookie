package com.avad.ebookie.domain.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.avad.ebookie.config.exception.ErrorCode;
import com.avad.ebookie.domain.common.exception.ForbiddenException;
import com.avad.ebookie.domain.common.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order.dto.request.OrderCreateRequestDto;
import com.avad.ebookie.domain.order.dto.response.OrderPageDetailResponseDto;
import com.avad.ebookie.domain.order.dto.response.OrderPageResponseDto;
import com.avad.ebookie.domain.order.dto.response.OrderResponseDto;
import com.avad.ebookie.domain.order.entity.Order;
import com.avad.ebookie.domain.order.entity.OrderStatus;
import com.avad.ebookie.domain.order.mapper.OrderMapper;
import com.avad.ebookie.domain.order.repository.OrderRepository;
import com.avad.ebookie.domain.order_detail.dto.response.OrderDetailResponseDto;
import com.avad.ebookie.domain.order_detail.entity.OrderDetail;
import com.avad.ebookie.domain.order_detail.mapper.OrderDetailMapper;
import com.avad.ebookie.domain.order_detail.repository.OrderDetailRepository;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
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
//        Status newOrderStatus = statusRepository.findByName("신규주문");
        Order newOrder = Order.builder()
                .member(loggedInMember)
                .orderStatus(OrderStatus.PENDING)
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

        // get Order
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        // 주문, 회원 검사
        boolean cannotAccess = !order.getMember().getId().equals(loggedInMember.getId());
        if (cannotAccess) {
            throw new ForbiddenException(ErrorCode.INSUFFICIENT_RIGHTS);
        }

        // get all orderdetails
        List<OrderDetail> orderDetails = order.getOrderDetails();
        List<OrderDetailResponseDto> orderDetailDtos = orderDetailMapper.toDtoList(orderDetails);


        return OrderPageDetailResponseDto.builder()
                .orderStatus(order.getOrderStatus())
                .orderDetailDtos(orderDetailDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public OrderPageResponseDto getListOfOrders(Pageable pageable) {
        // get member
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        // get Orders with pagination, ordered by creation date descending
        Page<Order> orderPage = orderRepository.findByMemberOrderByCreatedAtDesc(loggedInMember, pageable);
        
        List<OrderResponseDto> orderResponseDtos = orderPage.getContent().stream()
                .map(orderMapper::toComplexDto)
                .collect(Collectors.toList());

        return OrderPageResponseDto.builder()
                .orders(orderResponseDtos)
                .totalPages(orderPage.getTotalPages())
                .totalElements(orderPage.getTotalElements())
                .currentPage(orderPage.getNumber())
                .build();
    }

}
