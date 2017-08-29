/**
 * 
 */
package com.sutha.id.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Muhammad Suta
 *
 */
public class BaseController {
	protected void encodeToJson(HttpServletResponse response, Map<String, Object> map){
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType("application/json");
		PrintWriter out;
		
		try {
			out = response.getWriter();
			objectMapper.writeValue(out, map);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
