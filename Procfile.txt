web: java -Dserver.port=$PORT $JAVA_OPTS -Xvirtualized -Xshareclasses" -Xshareclasses:name=sum -Xshareclasses:cacheDir=/app/_cache -jar target/mum-zinfi-2.2.0.RELEASE.jar
