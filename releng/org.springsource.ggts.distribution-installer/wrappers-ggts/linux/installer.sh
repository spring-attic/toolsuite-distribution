#!/bin/bash
# To specify the required version, set the REQUIRED_VERSION to the major version required, 
# e.g. 1.3, but not 1.3.1.
REQUIRED_VERSION=1.5
REQUIRED_VERSION_STRING=$REQUIRED_VERSION

error_msg() {
	echo "No Java Runtime Environment version >= $REQUIRED_VERSION_STRING found on your system. Please set your JAVA_HOME variable to point to a JRE or add a Java executable to your PATH."
}

# Transform the required version string into a number that can be used in comparisons
REQUIRED_VERSION=`echo $REQUIRED_VERSION | sed -e 's;\.;0;g'`
# Check JAVA_HOME directory to see if Java version is adequate
if [ $JAVA_HOME ]
then
	JAVA_EXE=$JAVA_HOME/bin/java
	$JAVA_EXE -version 2> tmp.ver
	VERSION=`cat tmp.ver | grep "java version" | awk '{ print substr($3, 2, length($3)-2); }'`
	rm tmp.ver
	VERSION=`echo $VERSION | awk '{ print substr($1, 1, 3); }' | sed -e 's;\.;0;g'`
	if [ $VERSION ]
	then
		if [ $VERSION -ge $REQUIRED_VERSION ]
		then
			JAVA_HOME=`echo $JAVA_EXE | awk '{ print substr($1, 1, length($1)-9); }'`
		else
			JAVA_HOME=
		fi
	else
		JAVA_HOME=
	fi
fi

# If the existing JAVA_HOME directory is adequate, then leave it alone
# otherwise, use 'locate' to search for other possible java candidates and
# check their versions.
if which locate >/dev/null; then
	if [ $JAVA_HOME ]
	then
		:
	else
		for JAVA_EXE in `locate bin/java | grep java$ | xargs echo`
		do
			if [ $JAVA_HOME ] 
			then
				:
			else
				$JAVA_EXE -version 2> tmp.ver 1> /dev/null
				VERSION=`cat tmp.ver | grep "java version" | awk '{ print substr($3, 2, length($3)-2); }'`
				rm tmp.ver
				VERSION=`echo $VERSION | awk '{ print substr($1, 1, 3); }' | sed -e 's;\.;0;g'`
				if [ $VERSION ]
				then
					if [ $VERSION -ge $REQUIRED_VERSION ]
					then
						JAVA_HOME=`echo $JAVA_EXE | awk '{ print substr($1, 1, length($1)-9); }'`
					fi
				fi
			fi
		done
	fi
fi

if [ $JAVA_HOME ]
then
	$JAVA_HOME/bin/java -jar $JAR_NAME.jar
else
	JAVA_EXE=`which java | grep java$ | xargs echo`
	$JAVA_EXE -version 2> tmp.ver 1> /dev/null
	VERSION=`cat tmp.ver | grep "java version" | awk '{ print substr($3, 2, length($3)-2); }'`
	rm tmp.ver
	VERSION=`echo $VERSION | awk '{ print substr($1, 1, 3); }' | sed -e 's;\.;0;g'`
	if [ $VERSION ]
	then
		if [ $VERSION -ge $REQUIRED_VERSION ]
		then
			$JAVA_EXE -jar $JAR_NAME.jar
		else 
			error_msg
		fi
	else 
		error_msg
	fi
fi
