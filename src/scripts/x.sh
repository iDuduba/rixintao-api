#!/bin/sh

#分析命令行参数
CONSOLE=false

while getopts :ci:v opt; do
	case "$opt" in
		c) CONSOLE=true ;;
		v) OPTION_VERBOSE=true ;;
		i) INSTANCE=${OPTARG} ;;
		\?)
			echo "Invalid option: -$OPTARG" >&2
			exit 1
			;;
		:)
			echo "Option -$OPTARG requires an argument." >&2
			exit 1
			;;			
	esac
done
shift $(( OPTIND - 1 )) # now do something with $@

if [ -z "$DM_BASE" ]; then
    DM_BASE_DIR=`dirname "$0"`
    DM_BASE_DIR=`( cd "$DM_BASE_DIR" && cd .. && pwd )`
fi
export DM_BASE_DIR

cd $DM_BASE_DIR

if [ -z "$JAVA_HOME" -a -z "$JRE_HOME" ]; then
    echo "Neither the JAVA_HOME nor the JRE_HOME environment variable is"
    echo "defined. At least one of these environment variable is needed"
    echo "to run this program"
    exit 1
fi
if [ -z "$JRE_HOME" ]; then
    JRE_HOME="$JAVA_HOME"
fi
_RUNJAVA="$JRE_HOME/bin/java"

JARPATH="$DM_BASE_DIR/lib"

CLASSPATH=
if [ ! -z "$CLASSPATH" ]; then
    CLASSPATH="$CLASSPATH"
fi

# 遍历应用程序依赖的jar包，并加入CLASSPATH。  
for jarfile in `ls lib/.`; do  
    CLASSPATH="${CLASSPATH}:lib/$jarfile"  
done

if [ -z "${INSTANCE}" ]; then
    INSTANCE=$(date "+%Y%m%d_%H%M%S")
fi
LOGGING_CONF="-Dlogfilename=log/$INSTANCE"
JAVA_OPTS="-Dlog4j.configuration=file:$DM_BASE_DIR/conf/log4j.xml"
VM_OPTS=-Xmx2048m

if ${CONSOLE}; then
	eval "$_RUNJAVA $JAVA_OPTS $VM_OPTS $LOGGING_CONF -classpath $CLASSPATH com.laic.sz.Main $@"
else 
	eval "setsid $_RUNJAVA $JAVA_OPTS $VM_OPTS $LOGGING_CONF -classpath $CLASSPATH com.laic.sz.Main $@ > /dev/null 2>&1 &"
fi

exit $?

