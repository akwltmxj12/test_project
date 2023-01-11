package com.sycompany.hsp.controller;

import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sycompany.hsp.dao.IDao;
import com.sycompany.hsp.dto.AppointmentInfoDto;
import com.sycompany.hsp.dto.HspInfoDto;
import com.sycompany.hsp.dto.Hsp_vaccineInfoDto;
import com.sycompany.hsp.dto.HspdayappDto;

import oracle.security.crypto.core.Padding.ID;

@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
		

	
	@RequestMapping("/test")
	public String test() {
		
		return "test";
	}
	
	
	@RequestMapping("/btn1")
	public String btn1() {
		
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
		String appCk;    // 예약확인
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		for(int i=0 ; i<100; i++) {
			
			
			
		}
		
		return "test";
	}
	

	@RequestMapping("/test2")
	//@PostMapping("/test2")
	public String test2(Model model, HttpSession session) {
		
		String sessionId = session.getAttribute("hspId").toString();
		
		Date date = new Date();
		String today = String.format("%1$tY-%1$tm-%1$td", date);
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		HspInfoDto hspInfo = dao.getHspInfo(sessionId); //의료기관 모든 정보
		String hspKinds = hspInfo.getHspVaccineCk();
 		String hspKind[] = hspKinds.split("> ");
		
		List<AppointmentInfoDto> allApp = dao.allAppInfo(today);
		
		
	
		model.addAttribute("test", creatApp(sessionId, allApp));
		
		
		return "test2";
	}

	
	private List<AppointmentInfoDto> creatApp(String sessionId, List<AppointmentInfoDto> allApp) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		HspInfoDto hspInfo = dao.getHspInfo(sessionId); //의료기관 모든 정보
		String hspKinds = hspInfo.getHspVaccineCk();
 		String hspKind[] = hspKinds.split("> ");
		
		List<AppointmentInfoDto> combo = new ArrayList<>();
		
		String aName = "";
		String bName ="";
		for(int i=0; i<allApp.size(); i++) {
			aName = allApp.get(i).getVaccineName().toString();
			
			for(int j=0; j<hspKind.length; j++) {
				bName = hspKind[j];
				if(aName.contains(bName)) {
					combo.add(allApp.get(i));
					break;
				}else {
				
				}
			}
		}
		
		
		return combo;
		
	}
	
}
