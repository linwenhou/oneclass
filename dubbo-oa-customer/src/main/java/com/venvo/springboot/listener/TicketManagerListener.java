package com.venvo.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.venvo.springboot.config.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import weixin.popular.support.TicketManager;

@WebListener
public class TicketManagerListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(TicketManagerListener.class);
	
	@Autowired
	WxConfig wxConf;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("------------------TicketManagerListener----contextInitialized---------------");
		TicketManager.init(wxConf.getAppID(), 15, 60 * 119);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("------------------TicketManagerListener----contextInitialized---------------");
		TicketManager.destroyed();
	}

}
