package com.tvi.app

import java.util
import java.util.Calendar

import com.tvi.app.TariffManager.format

/**
  * Tariff Manager
  */
object TariffManager {

  private val format =
    new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

//  The current tariff
  private val tariffList: util.ArrayList[Tariff] = new util.ArrayList[Tariff]

  /**
    * Returns the current tariff information
    * @return
    */
  def getCurrentTariff(): Option[Tariff] = {
    tariffList.isEmpty match {
      case true => None
      case false => Some(tariffList.get(0))
    }
  }

  /**
    * Sets a new tariff
    * @param tariff the tariff to set
    * @return a string detailing the operation result
    */
  def set(tariff: Tariff): String = {
    // 1) the activeStarting value should be later than the current time
    format
      .parse(tariff.activeStarting)
      .after(Calendar.getInstance().getTime) match {
      case true => {
        tariffList.isEmpty match {
          case true => {
            tariffList.add(tariff)
            "First tariff set: " + tariff.toString
          }
          case false => {
            // 2) the activeStarting value should be later than the latest activeStarting value
            format
              .parse(tariff.activeStarting)
              .after(format.parse(tariffList.get(0).activeStarting)) match {
              case true => {
                tariffList.set(0, tariff)
                "New tariff set: " + tariff.toString
              }
              case false =>
                "Tariff not set: " + tariff.toString
            }
          }
        }
      }
      case false =>
        "Tariff not set: " + tariff.toString
    }
  }
}
