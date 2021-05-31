package com.zb

object Test {
  def main(args: Array[String]): Unit = {

    // 虽然 Scala 可以不定义变量的类型，不过为了清楚些，我还是
    // 把他显示的定义上了

    val myMap: Map[String, String] = Map("key1" -> "value")
    val value1: Option[String] = myMap.get("key1")
    val value2: Option[String] = myMap.get("key2")


    val sites = Map("runoob" -> "www.runoob.com", "google" -> "www.google.com")

    println("sites.get( \"runoob\" ) : " +  sites.get( "runoob" )) // Some(www.runoob.com)
    println("sites.get( \"baidu\" ) : " +  sites.get( "baidu" ))  //  None



  }
}
