package com.ridh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {

	public RecordNotFoundException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

}
