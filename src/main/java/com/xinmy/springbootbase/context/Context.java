package com.xinmy.springbootbase.context;

import java.util.Date;

/**
 *
 * @desc
 */
public interface Context {
	//
	String currentToken();

	//
	IUser currentUser();

	// 是否已登录.
	boolean isAuthorized();

	//
	IUser asUser(IUser user);

	void setToken(String action, String token);

	String getToken(String action);

	Date getLastAccessTime();

	Date getLoginTime();

	void setLoginTime(Date loginTime);

	double getLongitude();

	void setLongitude(double longitude);

	double getLatitude();

	void setLatitude(double latitude);
}
