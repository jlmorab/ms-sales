package com.jlmorab.ms.sales.dto;

import java.util.List;

import com.jlmorab.ms.sales.entity.TraOrder;
import com.jlmorab.ms.sales.entity.TraOrderDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private TraOrder order;
	private List<TraOrderDetail> details;
	
}
