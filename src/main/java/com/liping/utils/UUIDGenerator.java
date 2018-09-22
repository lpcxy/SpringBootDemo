package com.liping.utils;

import java.util.UUID;

public interface UUIDGenerator
{
	static String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
