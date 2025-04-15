package com.jlmorab.ms.sales.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderStatusEnumTest {

	@Test
	void fromValue_whenValueIsNumeric() {
		OrderStatusEnum expected = OrderStatusEnum.OPEN;
		
		OrderStatusEnum actual = OrderStatusEnum.fromValue( expected.getStatus() );
		
		assertNotNull( actual );
		assertEquals( expected, actual );
	}//end fromValue_whenValueIsNumeric()
	
	@Test
	void fromValue_whenValueIsStrig() {
		OrderStatusEnum expected = OrderStatusEnum.CLOSED;
		
		OrderStatusEnum actual = OrderStatusEnum.fromValue( expected.toString() );
		
		assertNotNull( actual );
		assertEquals( expected, actual );
	}//end fromValue_whenValueIsStrig()
	
	@Test
	void fromValue_whenValueIsUnknown() {
		assertThrows( IllegalArgumentException.class, () -> {
			OrderStatusEnum.fromValue( -1 );
		} );
		
		assertThrows( IllegalArgumentException.class, () -> {
			OrderStatusEnum.fromValue( "unknown" );
		} );
		
		assertThrows( IllegalArgumentException.class, () -> {
			OrderStatusEnum.fromValue( new Object() );
		} );
		
		assertThrows( NullPointerException.class, () -> {
			OrderStatusEnum.fromValue( null );
		} );
	}//end fromValue_whenValueIsUnknown()

}
