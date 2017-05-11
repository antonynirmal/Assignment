package my.sample.mapper.mysql;

import java.util.List;

import my.sample.annotations.MySqlDataSource;
import my.sample.models.Result;

@MySqlDataSource
public interface InfoMapper {
	
	public List<Integer> getList();
	
	public void push(Result r);
	
}
