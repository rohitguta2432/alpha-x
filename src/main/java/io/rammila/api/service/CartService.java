package io.rammila.api.service;

import io.rammila.api.constant.Constants;

import io.rammila.api.exception.AlphaException;
import io.rammila.api.model.Cart;
import io.rammila.api.repository.CartRepository;
import io.rammila.api.utility.AlphaUtils;
import io.rammila.api.utility.RoleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(Cart cart) {
        log.info("create cart : {} ", cart);

        if (ObjectUtils.isEmpty(cart)) {
            throw new AlphaException("cart cannot be null or empty");
        }
        if (ObjectUtils.isEmpty(cart.getProduct().getId())) {
            throw new AlphaException("productId cannot be Null or Empty");
        }
        Cart checkIfProductAlreadyExists = cartRepository.findByUserIdAndProductId(cart.getUserId(), cart.getProduct().getId());
        if (ObjectUtils.isEmpty(checkIfProductAlreadyExists)) {
            return cartRepository.save(cart);
        }
        checkIfProductAlreadyExists.setQuantity(cart.getQuantity());
        return cartRepository.save(checkIfProductAlreadyExists);
    }

    public String deleteToCart(UUID productId) {
        log.info("deleting from cart : {} ", productId);
            cartRepository.deleteById(productId);
        return "cart deleted successfully";
    }


    public List<Cart> getCart(UUID id) {
        log.info("fetching card by logged in user: {} ", id);
        List<Cart> carts = cartRepository.findAllByUserId(id);
        Double totalPrice = cartRepository.findAllByUserId(id).parallelStream().mapToDouble(p -> p.getTotalPricePerQuantity()).sum();
        return carts.stream()
                .peek(p -> p.setTotalPrice(totalPrice))
                .peek(t -> t.setTax(AlphaUtils.toDigit((totalPrice * Constants.TAX) / 100)))
                .peek(pt -> pt.setTotalPriceAfterTax(AlphaUtils.toDigit(pt.getTotalPrice() + pt.getTax())))
                .collect(Collectors.toList());
    }

    //@Cacheable(cacheNames = "carts",key = "#id")
    public Cart getById(UUID id) {
        log.info("fetching user cart by id : {} ", id);
        return cartRepository.findById(id).isPresent() ? cartRepository.findById(id).get() : null;
    }
}
