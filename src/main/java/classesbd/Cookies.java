package classesbd;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cookies {
	public static Cookie getCookieByName(String name, HttpServletRequest req){
		Cookie[] cookies = req.getCookies();
		Cookie umCookie = null;
		if (cookies != null){
			for(int i=0; i < cookies.length; i++){
				if(cookies[i].getName().equals(name)){
					umCookie = cookies[i];
					break;
				}
			}
		}
		return umCookie;
	}
	public static boolean setCookie(String name, String value, HttpServletResponse res){
		try{
			Cookie umCookie = new Cookie(name, value);
			umCookie.setMaxAge(12*60*60);//12 horas
			res.addCookie(umCookie);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static boolean removeCookie(String name, HttpServletResponse res){
		try{
			Cookie umCookie = new Cookie(name, "");
			umCookie.setMaxAge(0);
			res.addCookie(umCookie);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
