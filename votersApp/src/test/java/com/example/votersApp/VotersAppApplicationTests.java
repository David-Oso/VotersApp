package com.example.votersApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class VotersAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testDatabaseConnection(){
		DriverManagerDataSource dataSource =
				new DriverManagerDataSource("jdbc:mysql://127.0.0.1:3306");
		try{
			Connection connection = dataSource.getConnection("root", "3284368");
			System.out.println(connection);
			assertThat(connection).isNotNull();
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}
