package com.sycompany.hsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.hibernate.validator.constraints.Length;

public class Test {
	public static void main(String[] args) {
		
//	List<String> date = Arrays.asList();
//	for(int i=1; i<28; i++) {
//		String a = "22-" + "12-" + i;
//		if(i==3 && i==4 && i==10 && i==11 && i==17 && i==18 && i==24 && i==25) {
//			continue;
//		}else {
//			date.add(a);
//		}
//	}
	
		List<String> date = Arrays.asList();
		for(int i=1; i<28; i++) {
			String a = "22-" + "12-" + i;
			if(i==3 && i==4 && i==10 && i==11 && i==17 && i==18 && i==24 && i==25) {
				continue;
			}else {
				date.add(a);
			}
		}
		
		
	System.out.println(date); 

	}
}
