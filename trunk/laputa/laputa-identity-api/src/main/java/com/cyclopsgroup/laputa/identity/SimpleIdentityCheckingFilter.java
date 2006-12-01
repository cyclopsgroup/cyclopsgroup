package com.cyclopsgroup.laputa.identity;

import javax.servlet.FilterConfig;

public class SimpleIdentityCheckingFilter
    extends AbstractIdentityCheckingFilter
{
    @Override
    protected IdentityService createIdentityService( FilterConfig filterConfig )
        throws Exception
    {
        String className = filterConfig.getInitParameter( "identityServiceClass" );
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        IdentityService identityService = (IdentityService) contextClassLoader.loadClass( className ).newInstance();
        if ( identityService instanceof FilterConfigurable )
        {
            ( (FilterConfigurable) identityService ).configure( filterConfig );
        }
        return identityService;
    }
}