#!/bin/bash

################################################################################
# SymphonyBasic build file 
################################################################################

# The name of the ant build file to use
BUILDFILE=build.xml

# Root directory for the project
PROJECTDIR=.

# Directory where necessary build Java libraries are found
LIBDIR=${PROJECTDIR}/lib
TOMCATDIR=/home/tomek/Tools/apache-tomcat/apache-tomcat-7.0.30
TOMCATLIBDIR=${TOMCATDIR}/lib
RESULT="EMPTY"

#echo "STOP Tomcat"
#`rc.tomcat stop`

#--------------------------------------------
# set JAVA_HOME on Mac OSX
#--------------------------------------------
case "`uname`" in
  	Darwin*)
		if [ -z "$JAVA_HOME" ] ; then
			JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Home
		fi
        ;;
esac

#--------------------------------------------
# No need to edit anything past here
#--------------------------------------------

# Try to find Java Home directory, from JAVA_HOME environment
# or java executable found in PATH

if test -z "${JAVA_HOME}" ; then
   echo "ERROR: JAVA_HOME not found in your environment."
   echo "Please, set the JAVA_HOME variable in your environment to match the"
   echo "location of the Java Virtual Machine you want to use."
   exit
fi

# convert the existing path to unix
if [ `uname | grep -n CYGWIN` ]; then
   JAVA_HOME=`cygpath --path --unix "$JAVA_HOME"`
fi

# Define the java executable path
if [ "$JAVABIN" = "" ] ; then
  JAVABIN=${JAVA_HOME}/bin/java
fi

CLASSPATH=${ANT_HOME}/lib/ant-launcher.jar

# Try to include tools.jar for compilation
if test -f ${JAVA_HOME}/lib/tools.jar ; then
    CLASSPATH=${CLASSPATH}:${JAVA_HOME}/lib/tools.jar
    CLASSPATH=${CLASSPATH}:${TOMCATLIBDIR}/servlet-api.jar
fi

# convert the unix path to windows
if [ `uname | grep -n CYGWIN` ]; then
   CLASSPATH=`cygpath --path --windows "$CLASSPATH"`
fi

echo ${CLASSPATH}

# Call Ant

echo  ${JAVABIN} -cp "${CLASSPATH}" org.apache.tools.ant.launch.Launcher \
           -buildfile "${BUILDFILE}" "$@"

CMD="${JAVABIN} -cp "${CLASSPATH}" org.apache.tools.ant.launch.Launcher \
           -buildfile "${BUILDFILE}" "$@""

#exec ${JAVABIN} -cp "${CLASSPATH}" org.apache.tools.ant.launch.Launcher \
#           -buildfile "${BUILDFILE}" "$@" tee build.log
exec $CMD | tee build.log
RESULT=`grep SUCCESSFUL build.log`

sleep 1

if [ "$RESULT"  = "BUILD SUCCESSFUL" ]; then
  echo "OK :: $RESULT"
  #`rm -rf ${TOMCATDIR}/sympa.war`
  #sleep 3
  #`cp -rf ${DEVELOPDIR}/sympa.war $TOMCATDIR`
  `cp -rf ./sympa.war $TOMCATDIR`
fi

#sleep 3
#echo "START Tomcat" 
#`rc.tomcat start`
echo "end"

