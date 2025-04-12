package com.jlmorab.ms.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlmorab.ms.sales.entity.TraOrderDetail;

@Repository
public interface ITraOrderDetailRepository extends JpaRepository<TraOrderDetail, Integer> {}
