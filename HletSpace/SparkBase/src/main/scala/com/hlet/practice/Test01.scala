package com.hlet.practice

import org.apache.log4j.Logger
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark._


object Test01 {
  private val logRoot: Logger = Logger.getRootLogger

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "E:\\common\\hadoop_home")
    val conf = new SparkConf()
    conf.setAppName("Test01")
    conf.setMaster("local[*]")

    val spark = new SparkContext(conf)


    //读取文件获取数据
    val data = spark.textFile("E:\\codespace\\HletSpace\\SparkBase\\src\\main\\resources\\test.txt")
      .map(line => line.split("\\s+"))
      .map(line => (line(0).trim.toInt, line(1), line(2).trim.toInt, line(3), line(4), line(5).trim.toInt))

    //1.一共有多少个小于20岁的人参加考试？
    val c1 = data.filter(line => line._3 < 20).groupBy(_._2).count()
    println(s"一共有${c1}个小于20岁的人参加考试")
    //2.一共有多个男生参加考试？
    val c2 = data.filter(line => "男".equals(line._4)).groupBy(_._2).count()
    println(s"一共有${c2}男生参加考试")
    //3. 12班男生平均总成绩是多少？
    val mean1 = data.filter(line => line._1 == 12 && "男".equals(line._4)).map(x => (x._2, x._6)).groupByKey().map(x => (x._1, x._2.sum)).map(_._2).mean()
    val mean2 = data.filter(line => line._1 == 12 && "男".equals(line._4)).map(x => (x._2, x._6)).reduceByKey(_ + _).map(_._2).mean()
    println(mean1)
    println(mean2)
    //4.语文科目的平均成绩是多少？
    val mean3 = data.filter(line => "chinese".equals(line._5)).map(line => line._6).mean()
    println(mean3)
    //5.12班语文成绩最低分是多少？
    val min1 = data.filter(line => line._1 == 12 && "chinese".equals(line._5)).map(line => line._6).min()
    println(min1)
    //12.总成绩大于150分的12班的女生有几个？ filter(line => line._1 == 12 && line._4 == "女")
    val c3 = data.filter(line => line._1 == 12 && line._4 == "女").map(line => (line._2, line._6)).groupByKey().map(line => line._2.sum).filter(_ > 150).count()
    println(c3)

    //13.总成绩大于150分，且数学大于等于70，且年龄大于等于19岁的学生的平均成绩是多少？   先求的总成绩大于150分的学生成绩
    val sumScore = data.map(line => (line._2, line._6)).reduceByKey(_ + _)
    val tempStudent = data.filter(line => line._5.equals("math") && line._6 >= 70 && line._3 >= 19).map(line => (line._2, line._6))
    tempStudent.join(sumScore).map(line => (line._1, line._2._1, line._2._2)).foreach(println(_))
    spark.stop()
  }

}
