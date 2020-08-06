package io.rammila.api.repository;

import io.rammila.api.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    List<Cart> findAllByUserId(UUID userId);
    Cart findByUserIdAndProductId(UUID userId,UUID productId);
}
