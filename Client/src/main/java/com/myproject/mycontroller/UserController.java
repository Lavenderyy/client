package com.myproject.mycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.util.DBConnection;


@Controller
public class UserController {
	
	private DBConnection dbconnection;
	private Statement sta;
	private ResultSet rs;
	private String sql;
	
	/*** 
     * 用户登陆 
     * <p>注解配置，只允许POST提交到该方法 
     * @param username 
     * @param password 
     * @return 
     */  
    @RequestMapping(value="login",method=RequestMethod.POST)  
    public ModelAndView login(String username,String password){  
        //验证传递过来的参数是否正确，否则返回到登陆页面。  
        if(this.checkParams(new String[]{username,password})){  
            //指定要返回的页面为success.jsp  
            ModelAndView mav = new ModelAndView("success");  
            //将参数返回给页面  
            mav.addObject("username",username);  
            mav.addObject("password", password);  
            return mav;  
        }
      //指定要返回的页面为home.jsp  
        ModelAndView mav2 = new ModelAndView("home");  
        //将参数返回给页面  
        mav2.addObject("fail","failed");  
        return mav2;    
    }  
      
    /*** 
     * 验证参数是否为空 
     * @param params 
     * @return 
     */  
    private boolean checkParams(String[] params){  
    	boolean b=false;
		dbconnection=new DBConnection();
		sta=dbconnection.getStatement();
		sql="select * from user";
		try {
			rs=sta.executeQuery(sql);
			tag:
			while(rs.next()){
				String aname=rs.getString("username");
				String apass=rs.getString("password");
				
				if(aname.equals(params[0])&&apass.equals(params[1])){
					System.out.println("correct user.");
					b=true;
					break tag;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
    }

}
