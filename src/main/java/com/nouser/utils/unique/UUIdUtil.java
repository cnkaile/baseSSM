package com.nouser.utils.unique;

import java.util.UUID;

public class UUIdUtil {
	public static String uuid() {
		return uuid16();
	}

	public static String uuid16() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").toLowerCase();
	}

}
