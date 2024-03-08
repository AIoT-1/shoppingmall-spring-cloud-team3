package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.CartDto;
import com.nhnacademy.shoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDto.Read.Response>> getCart() {

        return ResponseEntity.ok().body(cartService.getCart());
    }
    @PostMapping
    public ResponseEntity<CartDto.Create.Response> addCart(@RequestBody @Valid CartDto.Create.Request request) {
        log.debug("productId: {}, quantity: {}", request.getProductId(), request.getQuantity());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartService.addCart(request.getProductId(), request.getQuantity()));
    }

}
