package com.sycompany.hsp.controller;

import java.io.PrintWriter;
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

import com.sycompany.hsp.dao.CheckIDao;
import com.sycompany.hsp.dao.SearchIDao;
import com.sycompany.hsp.dto.AppointmentInfoDto;
import com.sycompany.hsp.dto.HspInfoDto;

@Controller
public class CheckController {

	
	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping("/check")
	public String check(HttpSession session, Model model) {
		
		String sessionId = session.getAttribute("hspId").toString();
		
		Date date = new Date();
		String today = String.format("%1$tY-%1$tm-%1$td", date);
		
		CheckIDao ck = sqlSession.getMapper(CheckIDao.class);
		
		List<AppointmentInfoDto> allApp = ck.allAppInfo(today);
		HspInfoDto hspInfoDto = ck.getHspInfo(sessionId); // 로그인한 병원 정보 모두가져오기
		
		model.addAttribute("allApp", allApp); //모든 예약 정보 불러오기
		model.addAttribute("hspInfo", hspInfoDto); // 가입의료기관 정보
		
		return "check";
	}
	
	//@RequestMapping("/checkOk")
	@PostMapping("/checkOk")
	public String checkOk(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		String mName = request.getParameter("mNameClick"); // 접종자 이름
		String mJumin = request.getParameter("mJuminClick"); // 접종자 주민
		String[]mJumins = mJumin.split("-");
		String mJumin1 = mJumins[0];
		String mJumin2 = mJumins[1];
		String vaccineName = request.getParameter("vaccineNameClick"); // 접종한 백신명
		String sideEffect = request.getParameter("memo"); // 부작용
		
		CheckIDao ck = sqlSession.getMapper(CheckIDao.class);
		
		int updatesideEffect = ck.setSideEffect(mName, mJumin1, mJumin2, vaccineName, sideEffect);
		
		PrintWriter out;
		if(updatesideEffect>0){
			try {
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>alert('저장되었습니다.'); location.href='/check'; </script>");
				out.flush();
				return "redirect:check";
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>alert('문제가 발생하였습니다. 다시 시도해주세요.'); location.href='/check'; </script>");
				out.flush();
				return "redirect:check";
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:check";
	}
	
		//예약 검색한 결과 출력
		//@RequestMapping("/checkCondition")
		@PostMapping("/checkCondition")
		public String searchCondition(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
			
			String sessionId = session.getAttribute("hspId").toString(); //로그인 중이 아이디
			
			String todate = request.getParameter("todate"); // 조회하려는 최초의 날
			String dateApp = request.getParameter("dateApp"); // 조회하려는 마지막날
			String appTime1 = request.getParameter("appTime1"); // 조회시간
			String appTime2 = request.getParameter("appTime2"); // 조회시간
			String injectionName = request.getParameter("injectionName"); // 접종자 이름
			String jumin1 = request.getParameter("jumin1"); // 예약자 생년월일
			String jumin2 = request.getParameter("jumin2"); // 예약자 뒷번호
			
			CheckIDao ck = sqlSession.getMapper(CheckIDao.class);
			
			HspInfoDto hspInfoDto = ck.getHspInfo(sessionId); // 로그인한 병원 정보 모두가져오기
			
			PrintWriter out;
			//기간 
			if(todate != null && dateApp != null && appTime1.equals("시간선택") && appTime2.equals("시간선택") && injectionName == "" && jumin1 == "" && jumin2 == "") {
				
				List<AppointmentInfoDto> checkDayInfo = ck.dateCheckInfo(todate, dateApp);
					model.addAttribute("allApp", checkDayInfo);
					model.addAttribute("hspInfo", hspInfoDto); // 가입의료기관 정보
				
			}
			// 기간 + 생년월일
			else if(todate != null && dateApp != null && appTime1.equals("시간선택") && appTime2.equals("시간선택") && injectionName == "" && jumin1 != null && jumin2 == "") {
				List<AppointmentInfoDto> birthdayCheck = ck.birthdayCheckInfo(todate, dateApp, jumin1);
					model.addAttribute("allApp", birthdayCheck);
					model.addAttribute("hspInfo", hspInfoDto); // 가입의료기관 정보
			}
			// 기간 + 접종자명 + 생년월일
			else if(todate != null && dateApp != null && appTime1.equals("시간선택") && appTime2.equals("시간선택") && injectionName != null && jumin1 != null && jumin2 == "") {
				List<AppointmentInfoDto> birthNameCheck = ck.birthNameCheckInfo(todate, dateApp, injectionName, jumin1);
					model.addAttribute("allApp", birthNameCheck);
					model.addAttribute("hspInfo", hspInfoDto); // 가입의료기관 정보
			}
			// 기간 + 이름 + 주민번호
			else if(todate != null && dateApp != null && appTime1.equals("시간선택") && appTime2.equals("시간선택") && injectionName != null && jumin1 != null && jumin2 != null) {
				
				List<AppointmentInfoDto> checkInfo = ck.checkInfo(todate, dateApp, injectionName, jumin1, jumin2);
					model.addAttribute("allApp", checkInfo);
					model.addAttribute("hspInfo", hspInfoDto); // 가입의료기관 정보
				
			} 
			//기간 + 예약시간
			else if(todate != null && dateApp != null && !appTime1.equals("시간선택") && !appTime2.equals("시간선택") && injectionName == "" && jumin1 == "" && jumin2 == "") {
				List<AppointmentInfoDto> timeCheckInfo = ck.timeCheckInfo(todate, dateApp, 
																		appTime1, appTime2);
					model.addAttribute("allApp", timeCheckInfo);
					model.addAttribute("hspInfo", hspInfoDto); // 가입의료기관 정보
				
			}
			// 기간 + 시간 + 접종자명 + 주민번호
			else if(todate != null && dateApp != null && !appTime1.equals("시간선택") && !appTime2.equals("시간선택") && injectionName != null && jumin1 != null && jumin2 != null) {
				List<AppointmentInfoDto> allCheckInfo = ck.allCheckInfo(todate, dateApp, 
																		appTime1, appTime2, 
																		injectionName, 
																		jumin1, jumin2);
					model.addAttribute("allApp", allCheckInfo);
					model.addAttribute("hspInfo", hspInfoDto); // 가입의료기관 정보
				
			}	
			
			//무입력 시 첫화면으로 이동
			else if(todate == "" && dateApp == "" && appTime1.equals("시간선택") && appTime2.equals("시간선택") && injectionName == "" && jumin1 == "" && jumin2 == "") {
				return "redirect:check";
			}
	
			else {
				try {
					response.setContentType("text/html;charset=utf-8");
					out = response.getWriter();
					out.println("<script>alert('에러가 발생하였습니다. 다시 시도해주세요.'); location.href='/check'; </script>");
					out.flush();
					return "redirect:check";
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			return "check";
		}
	
}
