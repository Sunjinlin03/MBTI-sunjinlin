package com.qst;

public class ExamException extends RuntimeException {
	public ExamException() {
	}

	public ExamException(String msg) {
		super(msg);
	}

	public ExamException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
