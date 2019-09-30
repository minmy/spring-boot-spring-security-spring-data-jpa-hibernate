package com.xinmy.springbootbase.act;

import com.xinmy.springbootbase.context.Context;
import com.xinmy.springbootbase.context.ContextHolder;
import com.xinmy.springbootbase.helper.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @desc
 */
public class CommonAct {

	// 获取字段校验不通过的信息
	protected Map<String, String> getErrors(final BindingResult result) {
		Map<String, String> map = new HashMap<String, String>();
		List<FieldError> list = result.getFieldErrors();
		for (FieldError error : list) {
			System.out.println("error.getField():" + error.getField());
			System.out.println("error.getDefaultMessage():" + error.getDefaultMessage());

			map.put(error.getField(), error.getDefaultMessage());
		}
		return map;
	}

	//
	protected final Result error(final BindingResult result) {
		Context context = ContextHolder.currentContext();
		Map<String, String> map = this.getErrors(result);
		String errmsg = map.values().iterator().next();
		return new Result(false, "操作失败", errmsg);
	}
}
