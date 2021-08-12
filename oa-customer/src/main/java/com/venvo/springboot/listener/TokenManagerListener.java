package com.venvo.springboot.listener;

import com.venvo.springboot.config.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.popular.support.TokenManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 初始化Token监听器
 *
 */

@WebListener
public class TokenManagerListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(TicketManagerListener.class);
	@Autowired
    WxConfig wxConf;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("------------------TokenManagerListener----contextInitialized---------------");
			
		TokenManager.init(wxConf.getAppID(), wxConf.getAppsecret());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		logger.info("------------------TokenManagerListener----destroyed---------------");
		TokenManager.destroyed();
	}
}
