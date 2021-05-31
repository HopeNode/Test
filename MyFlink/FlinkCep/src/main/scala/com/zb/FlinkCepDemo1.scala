package com.zb

import org.apache.flink.api.common.eventtime.{Watermark, WatermarkGenerator, WatermarkGeneratorSupplier, WatermarkOutput, WatermarkStrategy}
import org.apache.flink.cep.CEP
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object FlinkCepDemo1 {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    //设置事件时间
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val data: DataStream[PayBean] = env.fromElements(PayBean(1L, "create", 1618536840000L), PayBean(1L, "pay", 1618536841000L), PayBean(2L, "create", 1618536972556L))

    val watermarked: DataStream[PayBean] = data.assignTimestampsAndWatermarks(new WatermarkStrategy[PayBean] {
      override def createWatermarkGenerator(context: WatermarkGeneratorSupplier.Context): WatermarkGenerator[PayBean] = {
        new WatermarkGenerator[PayBean] {
          var maxEventTime = 0L
          //准许延迟500毫秒
          var outTime = 500L

          // 事件进入
          override def onEvent(t: PayBean, l: Long, watermarkOutput: WatermarkOutput): Unit = {
            maxEventTime = Math.max(l, maxEventTime)
          }

          // 一个发散的操作
          override def onPeriodicEmit(watermarkOutput: WatermarkOutput): Unit = {
            watermarkOutput.emitWatermark(new Watermark(maxEventTime - outTime))
          }
        }
      }
    })


    val keyed: KeyedStream[PayBean, Long] = watermarked.keyBy(payBean => payBean.id)

    val pattern: Pattern[PayBean, PayBean] = Pattern.begin[PayBean]("start").where(payBean => payBean.state.equals("create")).next("end").where(payBean => payBean.state.equals("pay"))

    //   CEP.pattern(keyed, value)

    env.execute("FlinkCepDemo1")

  }

}

case class PayBean(id: Long, state: String, ts: Long)