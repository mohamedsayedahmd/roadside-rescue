package com.roadside.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@SpringBootTest
class BackendApplicationTests {
	Calulator underTest = new Calulator();
	@Test
	void itShouldAddNumbers() {
		// given
		int numberOne = 20;
		int numberTwo = 30;

		// when
		int result = underTest.add(numberOne,numberTwo);

		// then
		int expected = 50;
		assertThat(result).isEqualTo(expected);
	}
	class Calulator{
		int add(int a, int b) {
			return a + b;
		}
	}
}
