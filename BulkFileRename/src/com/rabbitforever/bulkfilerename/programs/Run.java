package com.rabbitforever.bulkfilerename.programs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.bundles.SysProperties;

public class Run {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	private static SysProperties sysProperties;
	public static void main(String[] args) {
		try{
			sysProperties = new SysProperties();
		} catch (Exception ex){
			logger.error(className + ".main()", ex);
			throw ex;
		} finally{

		}

	}

}
