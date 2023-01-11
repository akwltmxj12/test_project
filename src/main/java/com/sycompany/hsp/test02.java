package com.sycompany.hsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test02 {

	public static void main(String[] args) {
		
		// 12월
		List<String> date = new ArrayList<String>();
		for(int i=1; i<28; i++) {
			
			if(i!=3 && i!=4 && i!=10 && i!=11 && i!=17 && i!=18 && i!=24 && i!=25) {
				if(i<10){
					String a = "22-" + "12-" + "0" +i;
					date.add(a);
				}else {
					String a = "22-" + "12-" +i;
					date.add(a);
				}
			}else {
				continue;
			}
			
		}
		System.out.println(date);
	}
		
// 1월
//		List<String> date = new ArrayList<String>();
//		for(int i=1; i<28; i++) {
//			
//			if(i!=1 && i!=7 && i!=8 && i!=14 && i!=15 && i!=28 && i!=29) {
//				if(i<10){
//					String a = "22-" + "01-" + "0" +i;
//					date.add(a);
//				}else {
//					String a = "22-" + "01-" + i;
//					date.add(a);
//				}
//			}else{
//				continue;
//			}
//			
//		}
		
		//System.out.println(date); 

		//}
	

}
