package org.great.ctrl;

public class BusinessDaoFactory {
	private static BusinessDao businessDao = null;
	private static BusinessDaoFactory instance = null;
	
	private BusinessDaoFactory() {
		try {
			Class<?>clazz = Class.forName("org.great.ctrl.BusinessDaoImpl");
			businessDao = (BusinessDao) clazz.newInstance(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static BusinessDaoFactory getInstance()
	{
		if(instance == null)
		{
			instance = new BusinessDaoFactory();
		}
		return instance;
	}
	
	
	public BusinessDao getBusinessDao()
	{
		return businessDao;
	}
}
