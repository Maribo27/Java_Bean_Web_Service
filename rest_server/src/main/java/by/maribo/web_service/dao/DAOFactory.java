package by.maribo.web_service.dao;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final EntityDAO entityDAO = new EntityDAO();
    private final MethodDAO methodDAO = new MethodDAO();

    private DAOFactory() {}

    public EntityDAO getEntityDAO() {
        return entityDAO;
    }

    public MethodDAO getMethodDAO() {
        return methodDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}