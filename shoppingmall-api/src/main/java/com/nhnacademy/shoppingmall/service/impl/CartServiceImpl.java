package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.CartDto;
import com.nhnacademy.shoppingmall.enitiy.Cart;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.exception.cart.CartItemNotFoundException;
import com.nhnacademy.shoppingmall.exception.cart.DuplicateCartException;
import com.nhnacademy.shoppingmall.exception.product.ProductNotFoundException;
import com.nhnacademy.shoppingmall.exception.user.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.CartRepository;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.CartService;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public List<CartDto.Read.Response> getCart() {
        List<Cart> cartList = cartRepository.findByUser_Id(UserIdStore.getUserId());

        return cartList.stream()
                .map(CartDto.Read.Response::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CartDto.Create.Response addCart(Long productId, Integer quantity) {
        Long userId = UserIdStore.getUserId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (cartRepository.existsByUser_IdAndProduct_Id(userId, productId)) {
            throw new DuplicateCartException(productId);
        }
        Cart savedCart = cartRepository.save(Cart.builder()
                .product(product)
                .quantity(quantity)
                .user(user)
                .build());

        return CartDto.Create.Response.fromEntity(savedCart);
    }

    @Override
    public void deleteCart(Long cartId) {
        if(!cartRepository.existsById(cartId)){
            throw new CartItemNotFoundException(cartId);
        }
        cartRepository.deleteById(cartId);
    }

    @Override
    public CartDto.Update.Response updateCartItemQuantity(Long cartId, CartDto.Update.Request request) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartItemNotFoundException(cartId));
        cart.changeQuantity(request.getQuantity());
        return CartDto.Update.Response.fromEntity(cart);
    }
}
