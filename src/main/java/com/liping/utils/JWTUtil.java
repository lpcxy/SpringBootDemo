package com.liping.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.liping.domain.IamUser;
import com.liping.exception.ServiceException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * jwt工具类，进行jwt加解密
 * */
@Component
public class JWTUtil
{
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class.getName());
	private static String secret = readSeret();
	
	private static String readSeret(){
		BufferedReader reader = null;
        String result = "";
        try{
        	File file = ResourceUtils.getFile("classpath:jwt_key");
        	reader = new BufferedReader(new FileReader(file));
        	String tempString = null;
        	while ((tempString = reader.readLine()) != null) {
        		result += tempString;
            }
        }catch(Exception e){
        	LOGGER.error("read jwt key error");
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                	LOGGER.error("release reader resource error");
                }
            }
        }
        return result;
	}
	
	/**
	 * 生成token
	 * */
	private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + 86400L * 1000); //24小时
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }
	/**
	 * 解析token
	 * */
	private static Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
	/**
	 * 根据用户信息生成token
	 * */
	public static String getToken(IamUser user){
		Map<String, Object> claims = new HashMap<>();
		claims.put("user", user);
		return generateToken(claims);
	}
	/**
	 * 根据解析token
	 * */
	public static IamUser parseToken(String token){
		Claims claims = getClaimsFromToken(token);
		IamUser user =  JsonUtil.parseObject(JsonUtil.toJson(claims.get("user")), IamUser.class);
		user.setExpiration((Date) claims.getExpiration());
		return user;
	}
	/**
	 * 判断是否过期
	 * */
	public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = (Date) claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
	public static Boolean isTokenExpired(IamUser user) {
        try {
            return user.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

	/**
	 * 验证
	 * */
	public static Boolean isValidToken(String token) {
		if(isTokenExpired(token)){
			return false;
		}
		return true;
    }
	
	/**
	 * 加密
	 * */
	public static String encode(String key) throws ServiceException{
		if(key == null){
			LOGGER.error("parameter is null");
			throw new ServiceException(ErrorConstans.NULL_PARAMETER_ERROR, "parameter is null");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("key", key);
		return Jwts.builder().setClaims(map).signWith(SignatureAlgorithm.HS512, secret).compact(); 
	}
	/**
	 * 解密
	 * */
	 public static String decode(String value) throws ServiceException{
		 if(value == null){
			LOGGER.error("parameter is null");
			throw new ServiceException(ErrorConstans.NULL_PARAMETER_ERROR, "parameter is null");
		 }
		 try{
			 Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(value).getBody();
			 if(claims == null){
				LOGGER.error("decode message error");
				throw new ServiceException(ErrorConstans.DECODE_MESSAGE_ERROR, "decode message error");
			 }
			 return (String)claims.get("key");
		 }catch(ServiceException sE){
			 throw sE;
		 }catch(Exception e){
			LOGGER.error("decode message error");
			throw new ServiceException(ErrorConstans.DECODE_MESSAGE_ERROR, "decode message error");
		 }
	 }
}
