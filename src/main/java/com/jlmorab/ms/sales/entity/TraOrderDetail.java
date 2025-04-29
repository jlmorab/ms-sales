package com.jlmorab.ms.sales.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tra_order_detail", schema = "sales", 
	   uniqueConstraints = @UniqueConstraint(name = "un_tra_order_detail_definition", columnNames = { "ord_id", "prod_id" }))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraOrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer oddId;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ord_id", nullable = false, foreignKey = @ForeignKey(name = "fk_tra_order_detail_order"))
	TraOrder ordId;
	
	@Column(name = "odd_amount", nullable = false)
	int oddAmount;
	
	@Column(name = "prod_id", nullable = false)
	int prodId;
	
	@Column(name = "odd_price", nullable = false)
	double oddPrice;
	
	@Column(name = "odd_discount", nullable = false)
	double oddDiscount;
	
}