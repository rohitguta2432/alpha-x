package io.rammila.api.controller;

import io.rammila.api.model.Cart;

import io.rammila.api.service.CartService;
import io.rammila.api.utility.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<?> addToCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.createCart(cart), HttpStatus.OK);
    }

    @DeleteMapping("{productId}")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<?> deleteToCart(@PathVariable UUID productId) {
        return new ResponseEntity<>(cartService.deleteToCart(productId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<?> getCartByUserId() {
        return new ResponseEntity<>(cartService.getCart(RoleUtil.getCurrentUseInfo().getId()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('CREATE_USER')")
    public ResponseEntity<?> getCartById(@PathVariable UUID id){
        return new ResponseEntity<>(cartService.getById(id),HttpStatus.OK);
    }
}
