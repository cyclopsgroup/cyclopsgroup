package org.cyclopsgroup.jmxterm.jdk5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.jmxterm.JavaProcess;
import org.cyclopsgroup.jmxterm.JavaProcessManager;
import org.cyclopsgroup.jmxterm.WeakCastUtils;

/**
 * JDK5 specific implementation of {@link JavaProcessManager}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class Jdk5JavaProcessManager
    extends JavaProcessManager
{
    private static final String CLASS_HOST_IDENTIFIER = "sun.jvmstat.monitor.HostIdentifier";

    private static final String CLASS_MONITORED_VM = "sun.jvmstat.monitor.MonitoredVm";

    private static final String CLASS_MONITORED_VM_UTIL = "sun.jvmstat.monitor.MonitoredVmUtil";

    private static final String CLASS_VM_IDENTIFIER = "sun.jvmstat.monitor.VmIdentifier";

    private static final Log LOG = LogFactory.getLog( Jdk5JavaProcessManager.class );

    private final ConnectorAddressLink connectorAddressLink;

    private final Method getMonitoredVm;

    private final MonitoredHost localhost;

    private final Object localhostDelegate;

    private final Class<?> monitoredVmType;

    private final Method toCommandLine;

    private final Constructor<?> vmIdentifierConstructor;

    /**
     * Default constructor
     * 
     * @throws Exception #findLocalhost() throws a lot of exceptions, let them go
     */
    public Jdk5JavaProcessManager()
        throws Exception
    {
        connectorAddressLink =
            WeakCastUtils.staticCast( Class.forName( ConnectorAddressLink.ORIGINAL_CLASS_NAME ),
                                      ConnectorAddressLink.class );

        Class<?> hic = Class.forName( CLASS_HOST_IDENTIFIER );
        Object hi = hic.getConstructor( String.class ).newInstance( (String) null );

        Class<?> mhc = Class.forName( MonitoredHost.ORIGINAL_CLASS_NAME );
        Method getInstance = mhc.getMethod( "getMonitoredHost", hic );
        localhostDelegate = getInstance.invoke( null, hi );
        localhost = WeakCastUtils.cast( localhostDelegate, MonitoredHost.class );
        Class<?> monitoredVmUtilClass = Class.forName( CLASS_MONITORED_VM_UTIL );
        monitoredVmType = Class.forName( CLASS_MONITORED_VM );
        toCommandLine = monitoredVmUtilClass.getMethod( "commandLine", monitoredVmType );

        Class<?> vmIdentifierType = Class.forName( CLASS_VM_IDENTIFIER );
        vmIdentifierConstructor = vmIdentifierType.getConstructor( String.class );
        getMonitoredVm = mhc.getMethod( "getMonitoredVm", vmIdentifierType );
    }

    /**
     * @inheritDoc
     */
    @Override
    public JavaProcess get( int pid )
        throws InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Validate.isTrue( pid > 0, "PID " + pid + " isn't valid" );

        Object vmid = vmIdentifierConstructor.newInstance( String.valueOf( pid ) );
        Object vm = getMonitoredVm.invoke( localhostDelegate, vmid );
        System.out.println( vm.getClass().getName() );
        String cmd = (String) toCommandLine.invoke( vm );
        return new Jdk5JavaProcess( pid, cmd, connectorAddressLink );
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<JavaProcess> list()
    {
        Set<Integer> pids = localhost.activeVms();
        List<JavaProcess> result = new ArrayList<JavaProcess>( pids.size() );
        for ( int pid : pids )
        {
            try
            {
                result.add( get( pid ) );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
                if ( LOG.isDebugEnabled() )
                {
                    LOG.debug( "Couldn't get PID" + pid, e );
                }
            }
        }
        return result;
    }
}