package com.xinmy.springbootbase.context;

/**
 *
 * @desc
 */
public interface IUser {
	/**
	 *
	 * @return
	 */
	Long getId();

	/**
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * 
	 * @return
	 */
	String getToken();

	/**
	 * 
	 * @return
	 */
	void setToken(String token);
	/**
	 *
	 * @return
	 */
	Long getLogsId();
}
