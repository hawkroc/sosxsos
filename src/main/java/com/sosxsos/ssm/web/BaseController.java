package com.sosxsos.ssm.web;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.InitializingBean;


public class BaseController implements InitializingBean {

	// public final Logger logger = LoggerFactory.getLogger(this .getClass());
	// private final Logger logger = LogManager.getLogger(this .getClass());

	Logger logger = LogManager.getLogger("warn_log");

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
		logger.trace("trace level");
		logger.debug("debug level");
		logger.info("info level");
		logger.warn("warn level");
		logger.error("error level");
		logger.fatal("fatal level");
		if (1 != 0) {
			// throw new Exception("该值不能为0.......");
		}

	}

}
