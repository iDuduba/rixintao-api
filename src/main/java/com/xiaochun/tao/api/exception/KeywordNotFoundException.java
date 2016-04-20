/**
 * duduba
 * Created at 2016年4月18日 下午11:02:27
 */
package com.xiaochun.tao.api.exception;

/**
 * @author duduba
 *
 */
public class KeywordNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public KeywordNotFoundException(String key) {
		super(key + " not available");
	}
}
