package hu.iboard.coandco.datamagic.service;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class ServiceBase extends HibernateDaoSupport{

	protected Logger logger = Logger.getLogger(this.getClass());

}
