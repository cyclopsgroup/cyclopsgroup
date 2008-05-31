# This is the init script for starting up the
# Apache Tomcat server
#
# chkconfig: 345 91 10
# description: Starts and stops the Tomcat daemon.
#

export JAVA_HOME=/opt/local/sun/jdk
export CATALINA_HOME=/opt/local/apache/apache-tomcat
export CATALINA_BASE=/opt/var/tomcat
export CATALINA_OPTS="-Dcom.sun.management.jmxremote.port=8081 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"

start(){
  echo Running sudo -u www-data $CATALINA_HOME/bin/startup.sh
  sudo -u www-data $CATALINA_HOME/bin/startup.sh
  RETVAL=$?
  echo Tomcat service is started
}

stop(){
  sudo -u www-data $CATALINA_HOME/bin/shutdown.sh 
  RETVAL=$?
  echo
}

restart(){
  stop
  start
}


# See how we were called.
case "$1" in
start)
  start
  ;;
stop)
  stop
  ;;
status)
  ;;
restart)
  restart
  ;;
*)
  echo "Usage: $0 {start|stop|status|restart}"
  exit 1
esac

exit 0
