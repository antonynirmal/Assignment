package my.sample.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import my.sample.exceptions.CommonException;
import my.sample.mapper.mysql.InfoMapper;
import my.sample.models.Info;
import my.sample.models.Result;
import my.sample.services.InfoService;


@Service
public class InfoServiceImpl implements InfoService {
	
	@Autowired
	private InfoMapper infoMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);


	@Override
	public String push(int i1, int i2) throws CommonException {
		Result r = new Result();
		Gson gson = new Gson();
		r.setInt1(i1);
		r.setInt2(i2);
		r.setTot(i1+i2);
				
		String result = gson.toJson(r);
		//r.setResultJson(result);
		
		// call db
		infoMapper.push(r);
		
		return result;
	}

	@Override
	public List<Integer> list() throws CommonException {
		
		List<Integer> result = new ArrayList<>();
		
		//result.add(new Integer("100"));
		//result.add(new Integer("200"));
		//result.add(new Integer("300"));
		
		result = infoMapper.getList();
		return result;
	}
	
	
	

}
