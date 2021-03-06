package com.dudungtak.seproject.repository;

import com.dudungtak.seproject.entity.OrderGroup;
import com.dudungtak.seproject.entity.User;
import com.dudungtak.seproject.enumpackage.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
    Page<OrderGroup> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    List<OrderGroup> findByStatusOrderByCreatedAtAsc(OrderStatus status);
}
