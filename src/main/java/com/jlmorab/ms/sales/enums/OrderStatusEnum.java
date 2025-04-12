package com.jlmorab.ms.sales.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatusEnum {
	
	OPEN		(100, "open"),
	COMPLETE	(150, "complete"),
	SUPPLYING	(200, "supplying"),
	CHARGING	(300, "charging"),
	ATTENDED	(900, "attended"),
	CLOSED		(910, "closed"),
	REJECTED	(920, "rejected");
	
	@JsonValue
	private final int status;
	private final String tag;
	
	@JsonCreator
	public static OrderStatusEnum fromValue(Object value) {
		return switch (value) {
		    case Number number -> Arrays.stream(OrderStatusEnum.values())
				.filter(status -> status.getStatus() == number.intValue())
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown value: " + number.intValue()));
		    case String strValue -> Arrays.stream(OrderStatusEnum.values())
				.filter(status -> status.getTag().equalsIgnoreCase(strValue))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown value: " + strValue));
		    default -> throw new IllegalArgumentException("Unknown value: " + value);
		};//end switch
	}//end fromValue

}
