package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {

    Page<Point> findByUser_Id(Long userId, Pageable pageable);

}
