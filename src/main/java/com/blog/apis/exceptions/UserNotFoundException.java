package com.blog.apis.exceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {

    private final Integer userId;

    public UserNotFoundException(Integer userId2) {
        super("User not found with ID: " + userId2);
        this.userId = userId2;
    }

	/*
	 * public Integer getUserId() { return userId; }
	 */

	public Integer getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}
}