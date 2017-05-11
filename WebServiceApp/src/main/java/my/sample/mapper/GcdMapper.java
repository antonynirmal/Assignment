package my.sample.mapper;

import java.util.List;

import my.sample.annotations.MySqlDataSource;
import my.sample.exceptions.CommonException;
import my.sample.models.Result;

@MySqlDataSource
public interface GcdMapper {
	
	public List<Result> getGcd();
	
	public List<Integer> getList();
	
	public Integer getSum();
	
	public void update(Result r);
	
}
