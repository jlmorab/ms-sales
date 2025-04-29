package com.jlmorab.ms.sales.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jlmorab.ms.data.TestData;
import com.jlmorab.ms.data.dto.WebResponseDTO;
import com.jlmorab.ms.sales.dto.OrderDTO;
import com.jlmorab.ms.sales.service.ISalesService;

import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class SalesControllerTest {

	static final int 	ANY_INT 		= TestData.getRandom(1, 1000);
	static final int 	ANY_STATUS 		= TestData.getRandom(1, 1000);
	
	@InjectMocks
	SalesController controller;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	ISalesService service;
	
	@Test
	void getAllOrders_whenReturnsData() {
		WebResponseDTO expected = mock( WebResponseDTO.class );
		
		when( service.getAllOrders( any(), anyInt() ) ).thenReturn( expected );
		
		WebResponseDTO actual = controller.getAllOrders( response, ANY_STATUS );
		
		assertNotNull( actual );
		assertEquals( expected, actual );
	}//end getAllOrders_whenReturnsData()
	
	@Test
	void getOrderById_whenReturnsData() {
		WebResponseDTO expected = mock( WebResponseDTO.class );
		
		when( service.getOrderById( any(), anyInt() ) ).thenReturn( expected );
		
		WebResponseDTO actual = controller.getOrderById( response, ANY_STATUS );
		
		assertNotNull( actual );
		assertEquals( expected, actual );
	}//end getOrderById_whenReturnsData()
	
	@Test
	void postAddOrder_whenReturnsData() {
		WebResponseDTO expected = mock( WebResponseDTO.class );
		OrderDTO order = mock( OrderDTO.class );
		
		when( service.postAddOrder( any(), any() ) ).thenReturn( expected );
		
		WebResponseDTO actual = controller.postAddOrder( response, order );
		
		assertNotNull( actual );
		assertEquals( expected, actual );
	}//end postAddOrder_whenReturnsData()
	
	@Test
	void patchUpdateOrderStatus_whenReturnsData() {
		WebResponseDTO expected = mock( WebResponseDTO.class );
		
		when( service.patchUpdateOrderStatus( any(), anyInt(), anyInt() ) ).thenReturn( expected );
		
		WebResponseDTO actual = controller.patchUpdateOrderStatus( response, ANY_INT, ANY_STATUS );
		
		assertNotNull( actual );
		assertEquals( expected, actual );
	}//end patchUpdateOrderStatus_whenReturnsData()

}
