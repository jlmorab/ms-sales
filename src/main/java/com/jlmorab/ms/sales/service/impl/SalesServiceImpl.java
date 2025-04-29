package com.jlmorab.ms.sales.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jlmorab.ms.data.dto.WebResponseDTO;
import com.jlmorab.ms.exception.LogicException;
import com.jlmorab.ms.sales.dto.OrderDTO;
import com.jlmorab.ms.sales.entity.CatOrderStatus;
import com.jlmorab.ms.sales.entity.TraOrder;
import com.jlmorab.ms.sales.enums.OrderStatusEnum;
import com.jlmorab.ms.sales.repository.ITraOrderDetailRepository;
import com.jlmorab.ms.sales.repository.ITraOrderRepository;
import com.jlmorab.ms.sales.service.ISalesService;
import com.jlmorab.ms.service.ServiceAbstract;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesServiceImpl extends ServiceAbstract implements ISalesService {

	private final ITraOrderRepository orderRepository;
	private final ITraOrderDetailRepository detailRepository;
	
	@Override
	public WebResponseDTO getAllOrders( HttpServletResponse response, Integer status ) {
		return executeFlow( response, log, () -> {
			if( status == null ) {
				return orderRepository.findAll();
			} else {
				TraOrder data = TraOrder.builder()
						.ostId( CatOrderStatus.builder()
								.ostId( status )
								.build() )
						.build();
				return orderRepository.findAll( Example.of(data) );
			}//end if
		});
	}//end getAllOrders()
	
	@Override
	public WebResponseDTO getOrderById( HttpServletResponse response, int id ) {
		return executeFlow( response, log, () -> orderRepository.findById( id ).orElse(null) );
	}//end getOrderById()
	
	@Override
	public WebResponseDTO postAddOrder( HttpServletResponse response, OrderDTO order ) {
		return executeFlow( response, log, () -> {
			TraOrder saved = orderRepository.save( order.getOrder() );
			if( saved.getOrdId() != null ) {
				order.getDetails().forEach( detail -> {
					detail.setOrdId( saved );
					detailRepository.save( detail );
				});
			} else {
				throw new LogicException("Error saving order");
			}//end if
			return saved.getOrdId();
		});
	}//end postAddOrder()
	
	@Override
	public WebResponseDTO patchUpdateOrderStatus( HttpServletResponse response, int id, int status ) {
		return executeFlow( response, log, () -> {
			OrderStatusEnum orderStatus = OrderStatusEnum.fromValue( status );
			TraOrder order = orderRepository.findById( id )
					.orElseThrow( () -> new IllegalArgumentException("Order not found") );
			order.setOstId(  CatOrderStatus.builder()
					.ostId( orderStatus.getStatus() )
					.build() );
			order.setOrdUpdated( LocalDateTime.now() );
			return orderRepository.save( order );
		});
	}//end patchUpdateOrderStatus()
	
}
