package by.maribo.java_beans_handbook.structure.service;

import by.maribo.java_beans_handbook.entity.EntityService;
import by.maribo.java_beans_handbook.method.MethodService;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final MethodService methodService = new MethodService();
    private final EntityService entityService = new EntityService();

    private ServiceFactory() {}

    public MethodService getMethodService() {
        return methodService;
    }

    public EntityService getEntityService() {
        return entityService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}
