package by.maribo.web_service.dao;

import by.maribo.web_service.dao.implementation.EnterpriseJavaBeanDAO;
import by.maribo.web_service.dao.implementation.JavaBeanDAO;

public final class Factory {

	private static final Factory instance = new Factory();

	private final DAO javaBean = new JavaBeanDAO();
	private final DAO enterpriseJavaBean = new EnterpriseJavaBeanDAO();

	private Factory() {}

	public static Factory getInstance() {
		return instance;
	}

	public DAO getJavaBean() {
		return javaBean;
	}

	public DAO getEnterpriseJavaBean() {
		return enterpriseJavaBean;
	}
}