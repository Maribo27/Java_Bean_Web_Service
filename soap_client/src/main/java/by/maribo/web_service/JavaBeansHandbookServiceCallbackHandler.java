/**
 * JavaBeansHandbookServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package by.maribo.web_service;


/**
 *  JavaBeansHandbookServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class JavaBeansHandbookServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public JavaBeansHandbookServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public JavaBeansHandbookServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    // No methods generated for meps other than in-out

    // No methods generated for meps other than in-out

    // No methods generated for meps other than in-out

    // No methods generated for meps other than in-out

    // No methods generated for meps other than in-out

    /**
     * auto generated Axis2 call back method for getAllEntities method
     * override this method for handling normal response from getAllEntities operation
     */
    public void receiveResultgetAllEntities(
        JavaBeansHandbookServiceStub.GetAllEntitiesResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getAllEntities operation
     */
    public void receiveErrorgetAllEntities(Exception e) {
    }

    // No methods generated for meps other than in-out

    /**
     * auto generated Axis2 call back method for getAllMethods method
     * override this method for handling normal response from getAllMethods operation
     */
    public void receiveResultgetAllMethods(
        JavaBeansHandbookServiceStub.GetAllMethodsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getAllMethods operation
     */
    public void receiveErrorgetAllMethods(Exception e) {
    }
}
