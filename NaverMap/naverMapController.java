package ynjch.front.naver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import ynjch.common.domain.CommonAjaxVO;
import ynjch.front.naver.service.naverMapService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.*;
    
@Slf4j
@Controller
public class naverMapController {

    @Autowired
    private naverMapService service;
	
    // 길찾기 화면
    @RequestMapping("/naver/naverMap01.do")
    public String getNaverMap01(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        return "naver/naverMap01";
    }
	
    // 길찾기 화면
    @RequestMapping("/naver/naverMap02.do")
    public String getNaverMap02(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        return "naver/naverMap02";
    }

    // 길찾기
    @RequestMapping("/naver/getDirection.do")
    public @ResponseBody CommonAjaxVO getDirection(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
    	CommonAjaxVO cavo = new CommonAjaxVO();
    	
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	Map<String, Object> directinoInfo = service.getDirection(paramMap);
        resultMap.put("directinoInfo", directinoInfo);
        
        cavo.setData(resultMap);
        cavo.setCode("success");

        return cavo;
    }

}
