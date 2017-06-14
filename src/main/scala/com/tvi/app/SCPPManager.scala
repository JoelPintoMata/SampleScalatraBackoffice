package com.tvi.app

import java.util
import java.util.Calendar
import scala.collection.JavaConversions._

import org.joda.time.{DateTime, Hours}

object SCPPManager {

  private val format = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

  //
  private val scppList: util.List[SCPP] = new util.ArrayList[SCPP]

  /**
    * Prints a overview of the scpp per customer
    * @return
    */
  def get(): List[SCPP] = {
    scppList.toList
  }

  /**
    * Builds a html textual overview of the scpp(s) per customer
    * @return a textual overview of the scpps(s) per customer
    */
  def getOverview(): String = {
    var result: String = new String
    scppList.toList
      .groupBy(_.customerId)
      .foreach(scppAux => {
        result = result.concat(scppAux._1.toString).concat("<br>")
        scppAux._2.foreach(scpp => {
          result = result.concat(scppAux.toString).concat("<br>")
        })
      })
    result
  }

  /**
    * Adds a scpp
    * @param scpp the scpp
    * @return a string detailing the operation result
    */
  def add(scpp: SCPP): String = {
//    TODO reject scpp(s) if no current tariff is set?
    scppList.add(scpp)
    "SCPP added: " + scpp.toString + " fee " + fee(scpp)
  }

  /**
    * Calculates the fee for this scpp
    * @return the fee
    */
  def fee(scpp: SCPP): Double = {
    TariffManager.getCurrentTariff() match {
//      TODO what to do where? This cant be zero...
//      could have easily reject it in this case
//      , preferred to save it and allow to process it later on once an admin checks the scpp(s) with 0$ fees and fixes them
      case None => 0
      case Some(tariff) => {
        val d1: org.joda.time.DateTime = DateTime.parse(scpp.startTime)
        val d2: org.joda.time.DateTime = DateTime.parse(scpp.endTime)

        val hours = Hours.hoursBetween(d1, d2)

//        TODO no conversion of hours => int to double?
        tariff.startFee + (tariff.hourlyFee * hours.getHours) + (tariff.feePerKWh * scpp.volume)
      }
    }
  }
}
