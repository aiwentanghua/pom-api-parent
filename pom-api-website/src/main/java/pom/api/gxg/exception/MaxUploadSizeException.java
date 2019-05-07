package pom.api.gxg.exception;

import java.util.Map;

/**
* @author fz
* @version 0.1 (2018年12月18日 下午2:14:58)
* @since 0.1  文件上传异常
* @see
*/
public class MaxUploadSizeException extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;

	private Map<String, Object> data;

	public static final int TYPE_JSON = 1;

	public MaxUploadSizeException() {
		super();
	}

	public MaxUploadSizeException(String message) {
		super(message);
	}

	public MaxUploadSizeException(String message, int code) {
		super(message);
		this.code = code;
	}

	public MaxUploadSizeException(String message, int code, Map<String, Object> data) {
		super(message);
		this.code = code;
		this.data = data;
	}
	
	public MaxUploadSizeException(String message, Map<String, Object> data) {
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
