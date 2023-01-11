package com.sycompany.hsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test1 {

	public static void main(String[] args) {
		
		
		String appDate;     // 예약일
		String appTime;     // 예약시간
		String appName;   // 예약자 이름 
		String appTel;       // 예약자 전화번호
		String mName;   // 피접종자이름
		String mTel = null;          // 피접종자 전화번호
		String mJumin1;    // 피접종자 생년월일
		String mJumin2;    // 피접종자 주민번호 뒷자리
		String injecNum;      //예약 백신 접종 차수
		String vaccineName;   // 접종받을 백신 이름
		String appRoute;      // 접종예약 경로(해당 의료기관, 모바일, PC)
		String appMemo;       // 접종 예약 시 메모사항
		String lastInjecDate;  // 최근 접종일(= 마지막 접종일)
		String lastInjecName;  // 최근접종명: 마지막으로 받은 접종명
		String lastInjec;      // 최근접종 차수: 1차 또는 매년(인플루엔자만) 접종일자 필요, 없으면 처음이라고 작성
		String sideEffect;   // 이전 접종 시 부작용: 피접종자의 예전 부작용 사례 또는 현재 발생한 부작용 증상
		String mSgck;      // 피접종자 특이사항
		String rsCk;       // 접종가능 결과
		String rsTxt;       // 접종 안될 시 특이사항 작성
		String appCk;    // 예약확인

		
		//예약일
		
		//12월
		List<String> date12 = Arrays.asList(
				"22-12-01", "22-12-02", "22-12-05", "22-12-06", "22-12-07", "22-12-08", 
				"22-12-09", "22-12-12", "22-12-13", "22-12-14", "22-12-15", "22-12-16", "22-12-19", "22-12-20", "22-12-21", "22-12-22", "22-12-23", "22-12-26", "22-12-27"
				);
		//1월
		List<String> date01 = Arrays.asList(
				"22-01-02", "22-01-03", "22-01-04", "22-01-05", "22-01-06", "22-01-09",
				"22-01-10", "22-01-11", "22-01-12", "22-01-13", "22-01-16", "22-01-17",
				"22-01-18", "22-01-19", "22-01-20", "22-01-26", "22-01-27");
		
		//2월
		List<String> date02 = Arrays.asList(
				);

		
		Collections.shuffle(date12);
		appDate = date12.get(0);
		
		// 예약시간
		List<String> time = Arrays.asList("오전09시", "오전10시", "오전11시", "오후12시", "오후1시", "오후2시", "오후3시", "오후4시");
		
		Collections.shuffle(time);
		appTime = time.get(0);
		
		// 예약자명
		List<String> firstName = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
			        "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
			        "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
			        "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
			        "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
	    List<String> lastName = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
			        "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
			        "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
			        "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
			        "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
			        "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
			        "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
			        "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
			        "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
			        "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
			  
	    		Collections.shuffle(firstName);
			    Collections.shuffle(lastName);
		 mName = firstName.get(0) + lastName.get(0) + lastName.get(1);
		 
		 
		 // 예약자 전화번호
		 mTel = "010" + (int)(Math.random() * 8999)+1000;
		 
		 
		 // 접종자 생년월일
		 int ranYear = (int)(Math.random()*99+9);
		 int mYear = 0;
		
			for(int ii=22; ii<56; ii++ ) {
				if(ii == ranYear) {
					continue;
				}
				else {
					mYear= ranYear;
					break;
				}
			}
			
			String year = Integer.toString(mYear);
			List<String> month = Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12");
			List<String> day = Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
			Collections.shuffle(month);
			Collections.shuffle(day);
		 
		 mJumin1 = year + month.get(0) + day.get(0);
		 
		 //주민 뒷자리
		 String temp = "";
			String sum = "";
			for (int j = 0; j < 7; j++) {
				temp = Integer.toString((int)(Math.random()*10));
				sum = sum + temp;
			}
		 
		 mJumin2 = sum;
			
			
		 // 접종차수
		 List<String> num = Arrays.asList("1차", "2차");
		 Collections.shuffle(num);
				 
		 injecNum = num.get(0);
		 
		 //접종 받는 백신이름
		 List<String> vaccine = Arrays.asList(
										 "결핵(BCG, 피내용)",
										 "B형간염(HepB)",
										 "디프테리아/파상풍/백일해(DTaP)",
										 "디프테리아/파상풍/백일해/폴리오(DTaP-IPV)",
										 "디프테리아/파상풍/백일해/폴리오/Hib(DTaP-IPV/Hib)",
										 "디프테리아/파상풍/백일해(TdaP)",
										 "파상풍/디프테리아(Td)",
										 "폴리오(IPV)",
										 "b형헤모필루스인플루엔자",
										 "폐렴구균(PCV 10가)",
										 "폐렴구균(PCV 13가)",
										 "폐렴구균(PCV 23가)",
										 "홍역/유행성이하선염/풍진(MMR)",
										 "A형간염(HepA)");
		 Collections.shuffle(vaccine);
		 vaccineName = vaccine.get(0);
		 
		 
		 // 예약방법
		 List<String> route = Arrays.asList("모바일","PC");
		 appRoute = route.get(0);
		 
		//최근 접종일
		List<String> lastDay = Arrays.asList("21-12-02", "22-12-06", "21-12-07",
										 "21-12-13", "21-12-14", "21-12-15",
										 "21-12-17", "21-12-20", "21-12-27");
		Collections.shuffle(lastDay);
		lastInjecDate = lastDay.get(0);
		 
		 //마지막 접종 백신명
		 lastInjecName ="인플루엔자(독감)";
		 
		 
		 //접종차수
		 lastInjec="매년";
		 
		 
		 // 부작용
		 sideEffect = "없음";
		 
		 
		 //확인
		 appCk = "확인완료";
	}

}
