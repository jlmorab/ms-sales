package com.jlmorab.ms.sales.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;

import com.jlmorab.ms.data.TestData;
import com.jlmorab.ms.data.dto.WebResponseDTO;
import com.jlmorab.ms.sales.dto.OrderDTO;
import com.jlmorab.ms.sales.entity.TraOrder;
import com.jlmorab.ms.sales.entity.TraOrderDetail;
import com.jlmorab.ms.sales.enums.OrderStatusEnum;
import com.jlmorab.ms.sales.repository.ITraOrderDetailRepository;
import com.jlmorab.ms.sales.repository.ITraOrderRepository;

import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
class SalesServiceImplTest {
	
	private static final Exception 	TEST_EXCEPTION 	= new RuntimeException("test-exception");
	private static final Integer 	ANY_INT 		= TestData.getRandom(1, 1000);
	
	@InjectMocks
	SalesServiceImpl service;
	
	@Mock
	ITraOrderRepository orderRepository;
	
	@Mock
	ITraOrderDetailRepository detailRepository;
	
	@Mock
	HttpServletResponse response;
	
	@Test
	void getAllOrders_withoutStatus_whenRecordExists() {
		TraOrder expected = new TraOrder();
		
		when(  orderRepository.findAll() ).thenReturn( List.of( expected ) );
		
		WebResponseDTO actual = service.getAllOrders( response, null );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.OK.value(), actual.getMeta().getStatusCode() );
		assertNotNull( actual.getData() );
		assertThat( actual.getData() ).isInstanceOf( List.class );
		List<TraOrder> orders = (List<TraOrder>) actual.getData();
		assertThat( orders )
			.hasSize( 1 )
			.containsExactly( expected );
	}//end getAllOrders_withoutStatus_whenRecordExists()
	
	@Test
	void getAllOrders_withStatus_whenRecordExists() {
		TraOrder expected = new TraOrder();
		
		when( orderRepository.findAll( any(Example.class) ) ).thenReturn( List.of( expected ) );
		
		WebResponseDTO actual = service.getAllOrders( response, ANY_INT );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.OK.value(), actual.getMeta().getStatusCode() );
		assertNotNull( actual.getData() );
		assertThat( actual.getData() ).isInstanceOf( List.class );
		List<TraOrder> orders = (List<TraOrder>) actual.getData();
		assertThat( orders )
			.hasSize( 1 )
			.containsExactly( expected );
	}//end getAllOrders_withStatus_whenRecordExists()
	
	@Test
	void getAllOrders_whenThrowException() {
		when( orderRepository.findAll() ).thenThrow( TEST_EXCEPTION );
		
		WebResponseDTO actual = service.getAllOrders( response, null );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getMeta().getStatusCode() );
		assertNull( actual.getData() );
	}//end getAllOrders_whenThrowException()
	
	@Test
	void getOrderById_whenRecordExists() {
		TraOrder expected = new TraOrder();
		
		when( orderRepository.findById( anyInt() ) ).thenReturn( Optional.of(expected) );
		
		WebResponseDTO actual = service.getOrderById( response, ANY_INT );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.OK.value(), actual.getMeta().getStatusCode() );
		assertNotNull( actual.getData() );
		assertThat( actual.getData() ).isInstanceOf( TraOrder.class );
		assertEquals( expected, (TraOrder) actual.getData() );
	}//end getOrderById_whenRecordExists()
	
	@Test
	void getOrderById_whenRecordDontExists() {
		when( orderRepository.findById( anyInt() ) ).thenReturn( Optional.empty() );
		
		WebResponseDTO actual = service.getOrderById( response, ANY_INT );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.OK.value(), actual.getMeta().getStatusCode() );
		assertNull( actual.getData() );
	}//end getOrderById_whenRecordDontExists()
	
	@Test
	void getOrderById_whenThrowException() {
		when( orderRepository.findById( anyInt() ) ).thenThrow( TEST_EXCEPTION );
		
		WebResponseDTO actual = service.getOrderById( response, ANY_INT );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getMeta().getStatusCode() );
		assertNull( actual.getData() );
	}//end getOrderById_whenThrowException()
	
	@Test
	void postAddOrder_whenSavedCorrectly() {
		Integer expected = TestData.getRandom(1, 1000);
		TraOrder order = mock( TraOrder.class );
		TraOrderDetail detail = mock( TraOrderDetail.class );
		OrderDTO orderDTO = new OrderDTO( order, List.of( detail ) );
		
		when( order.getOrdId() ).thenReturn( expected );
		when( orderRepository.save( any() ) ).thenReturn( order );
		
		WebResponseDTO actual = service.postAddOrder( response, orderDTO );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.OK.value(), actual.getMeta().getStatusCode() );
		assertNotNull( actual.getData() );
		assertEquals( expected, (Integer) actual.getData() );
		verify( detailRepository ).save( detail );
	}//end postAddOrder_whenSavedCorrectly()
	
	@Test
	void postAddOrder_whenOrderIsNotSaved() {
		TraOrder order = mock( TraOrder.class );
		OrderDTO orderDTO = new OrderDTO( order, null );
		
		when( order.getOrdId() ).thenReturn( null );
		when( orderRepository.save( any() ) ).thenReturn( order );
		
		WebResponseDTO actual = service.postAddOrder( response, orderDTO );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getMeta().getStatusCode() );
		assertNull( actual.getData() );
	}//end postAddOrder_whenOrderIsNotSaved()
	
	@Test
	void patchUpdateOrderStatus_whenUpdatedCorrectly() {
		TraOrder order = mock( TraOrder.class );
		
		when( orderRepository.findById( anyInt() ) ).thenReturn( Optional.of(order) );
		when( orderRepository.save( any() ) ).thenReturn( order );
		
		WebResponseDTO actual = service.patchUpdateOrderStatus( response, ANY_INT, OrderStatusEnum.COMPLETE.getStatus() );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.OK.value(), actual.getMeta().getStatusCode() );
		assertNotNull( actual.getData() );
		assertEquals( order, (TraOrder) actual.getData() );
		verify( orderRepository ).save( order );
	}//end patchUpdateOrderStatus_whenUpdatedCorrectly()
	
	@Test
	void patchUpdateOrderStatus_whenOrderDoesntExist() {
		when( orderRepository.findById( anyInt() ) ).thenReturn( Optional.empty() );
		
		WebResponseDTO actual = service.patchUpdateOrderStatus( response, ANY_INT, OrderStatusEnum.COMPLETE.getStatus() );
		
		assertNotNull( actual );
		assertEquals( HttpStatus.BAD_REQUEST.value(), actual.getMeta().getStatusCode() );
		assertNull( actual.getData() );
	}//end patchUpdateOrderStatus_whenOrderDoesntExist()
}
