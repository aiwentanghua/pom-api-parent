package pom.api.gxg.exception;

import java.util.Map;

/**
* 用户登录异常处理 
* @author fz
* @version 0.1 (2018年8月16日 上午10:34:59)
* @since 0.1
* @see
*/
public class UserPwdException extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;

	private Map<String, Object> data;

	public static final int TYPE_JSON = 1;

	public UserPwdException() {
		super();
	}

	public UserPwdException(String message) {
		super(message);
	}

	public UserPwdException(String message, int code) {
		super(message);
		this.code = code;
	}

	public UserPwdException(String message, int code, Map<String, Object> data) {
		super(message);
		this.code = code;
		this.data = data;
	}
	
	public UserPwdException(String message, Map<String, Object> data) {
		super(message);
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
