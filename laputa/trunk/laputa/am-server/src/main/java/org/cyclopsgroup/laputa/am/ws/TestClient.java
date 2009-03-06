package org.cyclopsgroup.laputa.am.ws;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.cyclopsgroup.laputa.am.AccountManager;

public class TestClient
{
    public static void main( String[] args )
    {
        JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
        fac.setServiceClass( AccountManager.class );
        fac.setAddress( "http://localhost:9000/services/am" );
        AccountManager am = (AccountManager)fac.create();
        System.out.println( am.ping( "xyz" ) );
    }
}
