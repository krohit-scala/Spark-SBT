# Spark-SBT
Refer to `log4j.properties` for Log4J configurations.
Refer to `spark.conf` for specifying Spark configurations. The program reads Spark properties from this file and creates `SparkConf` object.
Ensure you add these varianles as VM variables for ensuring the logging happens correctly:

`-Dlog4j.configuration=file:log4j.properties`

`-Dspark.yarn.app.container.log.dir=app-logs`

`-Dlogfile.name=hello-spark.log`
