package pom.api.gxg.apiresult;

/**
 * 响应结果生成工具
 *
 * @author fz
 * @version 0.1 (2018年8月15日 上午11:22:11)
 * @since 0.1
 * @see
 */

public class ResultGenerator {

	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

	public static Result genSuccessResult() {
		return new Result().setP_code(ResultCode.SUCCESS).setP_message(DEFAULT_SUCCESS_MESSAGE);
	}

	public static Result genSuccessResult(Object data) {
		return new Result().setP_code(ResultCode.SUCCESS).setP_message(DEFAULT_SUCCESS_MESSAGE).setP_param(data);
	}

	public static Result getSuccessStock(int stock){


		return new Result().setP_code(ResultCode.SUCCESS).setP_message(DEFAULT_SUCCESS_MESSAGE).setP_qty(stock);
	}



	public static Result genFailResult(String message) {
		return new Result().setP_code(ResultCode.FAIL).setP_message(message);
	}

	public static String getDefaultSuccessMessage() {
		return DEFAULT_SUCCESS_MESSAGE;
	}
}
