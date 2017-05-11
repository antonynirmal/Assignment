package my.sample.exceptions;

public class CommonException extends RuntimeException {
	public CommonException(){
		super();
	}
	
	public CommonException(String msg){
		super(msg);
	}
	
	public CommonException(String msg, Throwable t){
		super(msg, t);
	}
	
	public String getErrorMesage(){
		return this.getMessage();
	}
}
