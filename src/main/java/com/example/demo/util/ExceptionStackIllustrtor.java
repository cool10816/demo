package com.example.demo.util;

public class ExceptionStackIllustrtor {

	public static String illusustrateExceptionStack(Throwable trwException) {
		if (trwException == null) {
			return "";
		}
		StackTraceElement[] stearyExceptionStackTrace = trwException.getStackTrace();
		StringBuilder stbStackInformation = (new StringBuilder("[").append(trwException.toString()).append("]")
				.append((stearyExceptionStackTrace.length > 0) ? " -> " : ""));

		for (int cnt = 0; cnt < stearyExceptionStackTrace.length; cnt++)
			stbStackInformation.append("[").append(stearyExceptionStackTrace[cnt].toString()).append("]")
					.append((cnt == stearyExceptionStackTrace.length - 1) ? "" : " @ ");
		return stbStackInformation.toString();
	}
}
