package spark.examples

import java.util.{Calendar, Properties}

import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import scala.io.Source

object HelloSpark extends Serializable {

  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    val dt = Calendar.getInstance
    val startTime = dt.getTime
    logger.info(s"Hello Spark program started at: ${startTime}")

    // For hardcoding the SparkConf object with properties
    // val sparkConf = new SparkConf
    // sparkConf.setAppName("Hello Spark")
    // sparkConf.setMaster("local")

    val spark = SparkSession.builder
      .config(getSparkConfObject)
      .getOrCreate
    // .config(sparkConf) // Reading from the hard coded SparkConf object

    val sampleData = List(
      ("Oliver Queen", "Green Arrow"),
      ("Matt Murdock", "Daredevil"),
      ("Slade Wilson", "Deathstroke"),
      ("Wilson Fisk", "Kingpin"),
      ("Bruce Wayne", "Batman")
    )

    import spark.implicits._
    val colNames = Seq("AlterEgo", "ActualName")
    val superheros = sampleData.toDF(colNames: _*)
    superheros.show

    superheros.foreach(x => logger.info(s"Actual Name: ${x.get(0)} and True Character: ${x.get(1)}"))
    spark.stop
    spark.close

    val endTime = dt.getTime
    logger.info(s"Program completed at: ${endTime}")
  }

  def getSparkConfObject: SparkConf = {
    val sparkConf = new SparkConf

    // Read the spark.conf file
    val props = new Properties
    props.load(Source.fromFile("spark.conf").bufferedReader)

    // For Scala version 2.12+
    // props.forEach((k, v) => sparkConf.set(k.toString, v.toString))

    // For Scala version less than 2.12 (as this version isn;t fully compatible with Java lambda)
    import scala.collection.JavaConverters._
    props.asScala.foreach(prop => sparkConf.set(prop._1, prop._2))

    // Return the SparkConf object
    sparkConf
  }

}
