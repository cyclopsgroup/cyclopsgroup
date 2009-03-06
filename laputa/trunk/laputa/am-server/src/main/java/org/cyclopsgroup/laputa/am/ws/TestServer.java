package org.cyclopsgroup.laputa.am.ws;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.cyclopsgroup.laputa.am.AccountManager;

public class TestServer
{
    public static void main( String[] args )
    {
        JaxWsServerFactoryBean fac = new JaxWsServerFactoryBean();
        fac.setServiceClass( AccountManager.class );
        fac.setServiceBean( new AccountManagerImpl() );
        fac.setAddress( "http://localhost:9000/services/am" );
        fac.create();
    }
}
