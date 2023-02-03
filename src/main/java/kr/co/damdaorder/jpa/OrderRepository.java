package kr.co.damdaorder.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    //find
    Optional<OrderEntity> findByOrderCode (String orderCode);
    List<OrderEntity> findByIdentity(String identity);

    //exists
    boolean existsByOrderCode(String orderCode);
}
