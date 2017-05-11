package my.sample.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;

import my.sample.exceptions.CommonException;
import my.sample.models.Info;
import my.sample.models.Json;
import my.sample.models.Result;
import my.sample.services.InfoService;

@RestController
@RequestMapping("/info")
public class InfoController {
		
	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	
	@Autowired
	private InfoService infoService;
	
	@RequestMapping(path="/push", method={RequestMethod.POST})
	public String push(
			@RequestParam(value = "i1", required = false) int i1,
			@RequestParam(value = "i2", required = false) int i2,
			final HttpServletRequest request,
			final HttpServletResponse response, Model model) throws Exception {
	
		String retval = null;

		try {
			retval = infoService.push(i1, i2);
			response.setStatus(200);
		} catch (Exception e) {
			logger.error("push() -  Exception caught!  " + e.getMessage(),e);
			model.addAttribute("error", e.getMessage());
			response.setStatus(500); // Internal Server Error
			throw new Exception(e.getMessage());
		}

		return retval;
	}
	
	@RequestMapping(path="/list", method={RequestMethod.GET})
	public List<Integer> list(
			final HttpServletRequest request,
			final HttpServletResponse response, Model model) throws Exception {
	
		List<Integer> retval = null;

		try {
			retval = infoService.list();
			
			//JsonArray json = JsonArray.fromObject(retval);
			System.out.println(Json.toJson(retval));
			
			response.setStatus(200);
		} catch (Exception e) {
			logger.error("list() -  Exception caught!  " + e.getMessage(),e);
			model.addAttribute("error", e.getMessage());
			response.setStatus(500); // Internal Server Error
			throw new Exception(e.getMessage());
		}

		return retval;
	}
	
}
	