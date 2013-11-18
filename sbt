java -Xmx1024M -Xss2M -XX:MaxPermSize=512m -XX:+CMSClassUnloadingEnabled -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar `dirname $0`/sbt-launch.jar "$@"
