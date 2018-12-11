package com.aisino.webs;

public class FPServiceAPIPortTypeProxy implements com.aisino.webs.FPServiceAPIPortType {
  private String _endpoint = null;
  private com.aisino.webs.FPServiceAPIPortType fPServiceAPIPortType = null;
  
  public FPServiceAPIPortTypeProxy() {
    _initFPServiceAPIPortTypeProxy();
  }
  
  public FPServiceAPIPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initFPServiceAPIPortTypeProxy();
  }
  
  private void _initFPServiceAPIPortTypeProxy() {
    try {
      fPServiceAPIPortType = (new com.aisino.webs.FPServiceAPILocator()).getFPServiceAPIHttpPort();
      if (fPServiceAPIPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)fPServiceAPIPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)fPServiceAPIPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (fPServiceAPIPortType != null)
      ((javax.xml.rpc.Stub)fPServiceAPIPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.aisino.webs.FPServiceAPIPortType getFPServiceAPIPortType() {
    if (fPServiceAPIPortType == null)
      _initFPServiceAPIPortTypeProxy();
    return fPServiceAPIPortType;
  }
  
  public com.aisino.utils.ResultInfo createUser(java.lang.String xmlStr) throws java.rmi.RemoteException{
    if (fPServiceAPIPortType == null)
      _initFPServiceAPIPortTypeProxy();
    return fPServiceAPIPortType.createUser(xmlStr);
  }
  
  
}