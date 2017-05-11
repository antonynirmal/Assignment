package my.sample.services;

import java.util.List;
import my.sample.models.Result;

import my.sample.exceptions.CommonException;

public interface InfoService {
	
	public String push(int i1, int i2)  throws CommonException;
	
	public List<Integer> list() throws CommonException;
	
	
}
