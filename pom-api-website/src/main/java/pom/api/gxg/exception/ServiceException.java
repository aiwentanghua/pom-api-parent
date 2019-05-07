package pom.api.gxg.exception;
/**
 * 服务（业务）异常处理
* @author fz
* @version 0.1 (2018年8月16日 上午10:32:04)
* @since 0.1
* @see
*/
public class ServiceException extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7555280444688726685L;
	
	public static final int TYPE_JSON = 1;
	public static final int TYPE_CLOSE = 2;
	private int code;
	
	public ServiceException() {
		super();
    }

    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, int code) {
    	super(message);
    	this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

	/**
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
