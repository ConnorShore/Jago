package com.engine.core;

public class Error {
	public static void fatalError(String message) {
		System.err.println(message);
		System.exit(-1);
	}
}