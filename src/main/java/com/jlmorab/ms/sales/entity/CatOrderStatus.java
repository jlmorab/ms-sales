package com.jlmorab.ms.sales.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_order_status", schema = "sales", 
	   uniqueConstraints = @UniqueConstraint(name = "un_cat_order_status", columnNames = "ost_name"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatOrderStatus {
	
	@Id
	@Column(name = "ost_id", nullable = false)
	private int ostId;
	
	@Column(name = "ost_name", length = 50, nullable = false)
	private String ostName;

}