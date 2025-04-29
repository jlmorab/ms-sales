package com.jlmorab.ms.sales.entity;

import java.time.LocalDateTime;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tra_order", schema = "sales")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ord_id", nullable = false)
	Integer ordId;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ost_id", nullable = false, foreignKey = @ForeignKey(name = "fk_tra_order_cat_order_status"))
	CatOrderStatus ostId;
	
	@Column(name = "cli_id", nullable = false)
	int cliId;
	
	@Column(name = "ord_created", nullable = false)
	LocalDateTime ordCreated;
	
	@Column(name = "ord_updated", nullable = false)
	LocalDateTime ordUpdated;
	
}