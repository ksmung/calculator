package com.novem;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class Calculator {
//test multi branch in jenkins
	@Cacheable("sum")
	public int sum(int a, int b) {
		return a+b;
	}
}
