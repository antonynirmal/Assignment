package my.sample.service;

import java.util.List;
import my.sample.exceptions.CommonException;
import my.sample.models.Result;

public interface GcdService {
	
	public Integer getGcd() throws CommonException;
	
	public List<Integer> getList() throws CommonException;
	
	public Integer gcdSum() throws CommonException;
	
	public Result update(Result r) throws CommonException;

}
