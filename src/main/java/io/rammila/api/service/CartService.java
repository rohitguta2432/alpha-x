package io.rammila.api.service;

import io.rammila.api.exception.GlobalException;
import io.rammila.api.model.Cart;
import io.rammila.api.repository.CartRepository;
import io.rammila.api.utility.RoleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(Cart cart) {
        log.info("create cart : {} ", cart);

        if (ObjectUtils.isEmpty(cart)) {
            throw new GlobalException("cart cannot be null or empty");
        }
        if (ObjectUtils.isEmpty(cart.getProduct().getId())) {
            throw new GlobalException("productId cannot be Null or Empty");
        }
        Cart checkIfProductAlreadyExists = cartRepository.findByUserIdAndProductId(cart.getUserId(), cart.getProduct().getId());
        if (ObjectUtils.isEmpty(checkIfProductAlreadyExists)) {
            return cartRepository.save(cart);
        }
        checkIfProductAlreadyExists.setQuantity(cart.getQuantity());
        return cartRepository.save(checkIfProductAlreadyExists);
    }

    public Cart deleteToCart(UUID productId) {
        log.info("deleting from cart : {} ", productId);
        Cart checkIfProductAlreadyExists = cartRepository.findByUserIdAndProductId(RoleUtil.getCurrentUseInfo().getId(),productId);
        checkIfProductAlreadyExists.setQuantity(checkIfProductAlreadyExists.getQuantity() -1);
        return  cartRepository.save(checkIfProductAlreadyExists);
    }

    public List<Cart> getCart(UUID id) {
        log.info("fetching card by logged in user: {} ",id);
        return cartRepository.findAllByUserId(id);
    }
    //@Cacheable(cacheNames = "carts",key = "#id")
    public Cart getById(UUID id){
        log.info("fetching user cart by id : {} ",id);
        return cartRepository.findById(id).isPresent() ? cartRepository.findById(id).get() : null;
    }
}
