package com.jlmorab.ms.sales.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlmorab.ms.data.dto.WebResponseDTO;
import com.jlmorab.ms.sales.dto.OrderDTO;
import com.jlmorab.ms.sales.service.ISalesService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {

	private final ISalesService service;
	
	@GetMapping("/orders/{status}")
	WebResponseDTO getAllOrders( HttpServletResponse response, @PathVariable Integer status ) {
		return service.getAllOrders( response, status );
	}//end getAllOrders()
	
	@GetMapping("/order/{id}")
	WebResponseDTO getOrderById( HttpServletResponse response, @PathVariable int id ) {
		return service.getOrderById( response, id );
	}//end getOrderById()
	
	@PostMapping("/save-order")
	WebResponseDTO postAddOrder( HttpServletResponse response, @RequestBody OrderDTO order ) {
		return service.postAddOrder( response, order );
	}//end postAddOrder()
	
	@PatchMapping("/update-order/{id}/{status}")
	WebResponseDTO patchUpdateOrderStatus( HttpServletResponse response, @PathVariable int id, @PathVariable int status ) {
		return service.patchUpdateOrderStatus( response, id, status );
	}//end patchUpdateOrderStatus()
	
}