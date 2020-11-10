package ynjch.front.naver.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import ynjch.common.domain.AttachmentVO;
import ynjch.common.except.BusinessException;
import ynjch.common.property.PropertyContextProxy;
import ynjch.common.util.DateUtil;
import ynjch.common.util.ExtndStringUtil;
import ynjch.common.util.FileUploadModule;
import ynjch.common.util.StringUtil;
import ynjch.front.naver.domain.ClassCreateVO;


@Slf4j
@Service
public class naverMapService {

	@Autowired
	private HttpServletRequest request;
    
    // 길찾기 1
    public Map < String , Object > getDirection ( Map < String , Object > paramMap ) throws Exception
	{
		Map < String , Object > resultList = new HashMap < String , Object > ( ) ;

		try
		{
			String start = ( String ) paramMap.get ( "start" ) ;
			String goal = ( String ) paramMap.get ( "goal" ) ;
			String apiId = ( String ) paramMap.get ( "X-NCP-APIGW-API-KEY-ID" ) ;
			String apiSecret = ( String ) paramMap.get ( "X-NCP-APIGW-API-KEY" ) ;
			

			String https_url = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start=" + start + "&goal=" + goal + "&option=traoptimal" ;
			URL url ;

			log.info ( "##########################################################################################" ) ;
			log.info ( "연결 시작 ::: {}, key={}" , apiId , apiSecret ) ;
			log.info ( "##########################################################################################" ) ;
			url = new URL ( https_url ) ;
			HttpsURLConnection con = ( HttpsURLConnection ) url.openConnection ( ) ;
			con.setConnectTimeout ( 5000 ) ;
			con.setReadTimeout ( 5000 ) ;
			con.setRequestProperty ( "Content-Type" , "application/json; charset=UTF-8" ) ;
			con.setRequestProperty ( "X-NCP-APIGW-API-KEY-ID" , apiId ) ;
			con.setRequestProperty ( "X-NCP-APIGW-API-KEY" , apiSecret ) ;
			con.setDoOutput ( true ) ;
			con.setDoInput ( true ) ;
			con.setRequestMethod ( "GET" ) ;
			log.info ( "##########################################################################################" ) ;
			log.info ( "연결 완료 코드  = " + con.getResponseCode ( ) ) ;
			log.info ( "연결 완료  Message = " + con.getResponseMessage ( ) ) ;
			log.info ( "##########################################################################################" ) ;

			//dump all the content
			resultList = getDirectionInfo ( con ) ;
		}
		catch ( SSLPeerUnverifiedException e )
		{
			e.printStackTrace ( ) ;
		}
		catch ( MalformedURLException e )
		{
			e.printStackTrace ( ) ;
		}
		catch ( IOException e )
		{
			e.printStackTrace ( ) ;
		}

		return resultList ;
    }

    // 길찾기 2
    @ SuppressWarnings ( "unchecked" )
	public static Map < String , Object > getDirectionInfo ( HttpsURLConnection con )
	{

		Map < String , Object > resulMap = new HashMap < String , Object > ( ) ;

		if ( con != null )
		{

			try
			{
				InputStream in = new BufferedInputStream ( con.getInputStream ( ) ) ;

				String result = org.apache.commons.io.IOUtils.toString ( in , "UTF-8" ) ;
				log.info ( "##########################################################################################" ) ;
				log.info ( "조회 내용   = " + result.toString ( ) ) ;
				log.info ( "##########################################################################################" ) ;
				JSONObject json = JSONObject.fromObject ( result ) ;

				log.info ( "##########################################################################################" ) ;
				log.info ( "route = " + json.get ( "route" ) ) ;
				log.info ( "##########################################################################################" ) ;

				resulMap = new ObjectMapper ( ).readValue ( result.toString ( ) , HashMap.class ) ;

				log.info ( "##########################################################################################" ) ;
				log.info ( "Map List 내용   = " + resulMap.toString ( ) ) ;
				log.info ( "##########################################################################################" ) ;

				in.close ( ) ;
			}
			catch ( IOException e )
			{
				e.printStackTrace ( ) ;
			}

		}

		return resulMap ;
	}
}
