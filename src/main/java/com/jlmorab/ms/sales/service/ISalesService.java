package com.jlmorab.ms.sales.service;

import com.jlmorab.ms.data.dto.WebResponseDTO;
import com.jlmorab.ms.sales.dto.OrderDTO;

import jakarta.servlet.http.HttpServletResponse;

public interface ISalesService {

	WebResponseDTO getAllOrders( HttpServletResponse response, Integer status );
	WebResponseDTO getOrderById( HttpServletResponse response, int id );
	WebResponseDTO postAddOrder( HttpServletResponse response, OrderDTO order );
	WebResponseDTO patchUpdateOrderStatus( HttpServletResponse response, int id, int status );
	
}
