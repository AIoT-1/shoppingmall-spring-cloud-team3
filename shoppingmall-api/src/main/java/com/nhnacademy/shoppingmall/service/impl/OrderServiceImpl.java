package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.OrderDto;
import com.nhnacademy.shoppingmall.enitiy.*;
import com.nhnacademy.shoppingmall.exception.address.AddressNotFoundException;
import com.nhnacademy.shoppingmall.exception.user.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.*;
import com.nhnacademy.shoppingmall.service.OrderService;
import com.nhnacademy.shoppingmall.service.PointService;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;
    private final PointRepository pointRepository;
    private final PointService pointService;

    @Override
    public Long createOrder(OrderDto.RegisterRequest request) {
        Long userId = UserIdStore.getUserId();
        Long addressId = request.getAddressId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException(addressId));

        Order order = orderRepository.save(request.toEntity(user, address));

        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<Cart> cartList = cartRepository.findAllById(request.getCartIdList());

        for (Cart cartItem : cartList) {
            Product product = cartItem.getProduct();
            // 상품 수량 확인 및 감소 (상품 수량이 부족한 경우 예외 발생)
            product.decreaseOrderQuantity(cartItem.getQuantity());
            orderDetailList.add(OrderDetail.builder()
                    .order(order)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .build());
        }
        // 장바구니 삭제
        cartRepository.deleteAll(cartList);
        // 주문 상세 저장 + 주문 금액 저장
        orderDetailRepository.saveAll(orderDetailList)
                .forEach(orderDetail -> order.increaseOrderPrice(orderDetail.getProduct().getUnitCost() * orderDetail.getQuantity()));
        // 유저 포인트로 결제 (결제 금액보다 적은 경우 예외 발생)
        user.payment(order.getPrice());
        // 결제 내역 저장
        pointRepository.save(Point.builder()
                .user(user)
                .transactionType("결제")
                .amount(-order.getPrice())
                .build());
        try {
            pointService.accruePointByOrder(user, order);
        } catch (Exception e) {
            log.info("point accrue failed : {}", e.getMessage());
        }
        return order.getId();
    }


    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto.ReadResponse> getOrderPage(Pageable pageable) {
        Long userId = UserIdStore.getUserId();
        return orderRepository.findByUser_Id(userId, pageable)
                .map(OrderDto.ReadResponse::fromEntity);
    }
}
