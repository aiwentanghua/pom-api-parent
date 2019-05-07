package pom.api.gxg.apiresult;

/**
 * 响应码枚举，参考HTTP状态码的语义
 * @author fz
 * @version 0.1 (2018年8月15日 上午11:19:29)
 * @since 0.1
 * @see
 */

public enum ResultCode {
	/**
	 * 成功
	 */
	SUCCESS(1),
	/**
	 * 失败
	 */
    FAIL(2),
    
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401),
    
    /**
     * 接口不存在
     */
    NOT_FOUND(404),
    
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500),
    
    /**
     *  业务异常
     */
    SERVICE_FAIL(402),
	
    /**
     *  权限异常
     */
    SHIRO_SERVICE_FAIL(402403),
    
    /**
     *  用户下线
     */
    USER_DOWNLINE(407),
    
	/**
     *  用户密码错误
     */
	PWD_ERROR(406),
	
	/**
     *  文件上传异常捕获
     */
	FILE_ERROR(408);

    public int p_code;

    ResultCode(int p_code) {
        this.p_code = p_code;
    }
}
