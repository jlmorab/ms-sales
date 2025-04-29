package com.jlmorab.ms.sales;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.jlmorab.ms.SpringApplicationCommon;
import com.jlmorab.ms.annotation.EnableAutoMockedJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableAutoMockedJpaRepositories("com.jlmorab.ms.sales.repository")
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
        })
@ExtendWith(MockitoExtension.class)
class ApplicationTest {
	
	@Test
	void contextLoads() {
		log.info("Application deploy correctly");
	}//end contextLoads()
	
	@Test
	void executeCommonSettings() {
		try {
			SpringApplicationCommon common = mock(SpringApplicationCommon.class);
			ReflectionTestUtils.setField(Application.class, "springApplication", common);
			
			Application.main(new String[] {});
			
			verify( common, times(1) ).init( any(String[].class) );
		} finally {
			ReflectionTestUtils.setField(Application.class, "springApplication", new SpringApplicationCommon());
		}//end try
	}//end executeCommonSettings()

}
