# This is the init script for starting up the
# Apache Tomcat server
#
# chkconfig: 345 91 10
# description: Starts and stops the Tomcat daemon.
#

export JAVA_HOME=/opt/local/sun/jdk
export CATALINA_HOME=/opt/local/apache/apache-tomcat
export CATALINA_BASE=/opt/var/tomcat

start(){
  sudo -u www-data $CATALINA_HOME/bin/startup.sh
  RETVAL=$?
  echo
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