package my.sample.service.impl;

import java.util.ArrayList;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.sample.exceptions.CommonException;
import my.sample.mapper.GcdMapper;
import my.sample.models.Result;
import my.sample.service.GcdService;



@Service
public class GcdServiceImpl implements GcdService {
	
	@Autowired
	private GcdMapper gcdMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(GcdServiceImpl.class);

	@Override
	public Integer getGcd() throws CommonException {
		int gcd;
		int int1;
		int int2;
		Result result = new Result();
		List<Result> resultList = new ArrayList<>();
		resultList = gcdMapper.getGcd();
		if (resultList.size() > 0){
			result = resultList.get(0);
			int1 = result.getInt1();
			int2 = result.getInt2();
			gcd = this.findGCD(int1,int2);
			result.setTot(gcd);
			this.update(result);
		}else {
			gcd = 0;
		}
		return gcd;
	}

	@Override
	public List<Integer> getList() throws CommonException {
		List<Integer> gcdList = new ArrayList<>();
		gcdList = gcdMapper.getList();
		return gcdList;
	}

	@Override
	public Integer gcdSum() throws CommonException {
		Integer iGcdSum = gcdMapper.getSum();
		return iGcdSum;
	}
	
	@Override
	public Result update(Result r) throws CommonException {
		gcdMapper.update(r);
		return r;
	}
	
	 /*
     * Java method to find GCD of two number
     * @return int
     */
    private int findGCD(int number1, int number2) {
        //base case
        if(number2 == 0){
            return number1;
        }
        return findGCD(number2, number1%number2);
    }


	
}