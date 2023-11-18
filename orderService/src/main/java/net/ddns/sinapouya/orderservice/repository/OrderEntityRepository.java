package net.ddns.sinapouya.orderservice.repository;

import net.ddns.sinapouya.orderservice.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Long> {
}
