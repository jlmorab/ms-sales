package com.jlmorab.ms.sales.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
	
	@ParameterizedTest
	@MethodSource("unknownValueProvider")
	void fromValue_whenValueIsUnknown( Exception exception, Object value ) {
		assertThrows( exception.getClass(), () -> {
			OrderStatusEnum.fromValue( value );
		} ); 
	}//end fromValue_whenValueIsUnknown()
	
	static Stream<Arguments> unknownValueProvider() {
		return Stream.of(
			Arguments.of( new IllegalArgumentException(), -1 ),
			Arguments.of( new IllegalArgumentException(), "unknown" ),
			Arguments.of( new IllegalArgumentException(), new Object() ),
			Arguments.of( new NullPointerException(), null )
		);
	}//end unknownValueProvider()

}
