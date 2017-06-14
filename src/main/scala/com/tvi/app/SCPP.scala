package com.tvi.app

/**
  * Service tariff case class
  * @param customerId A string identifying the customer who charged his car in this transaction
  * @param startTime The time the transaction started, in ISO8601 format with "Z" as the timezone specification
  * @param endTime The time the transaction ended, in ISO8601 format with "Z" as the timezone specification
  * @param volume The amount of energy consumed in kWh
  */
case class SCPP(customerId: String,
                startTime: String,
                endTime: String,
                volume: Double)
    extends CustomToString

/**
  * Custom SCPP toString method
  */
trait CustomToString { thisSCPP: SCPP =>
  override def toString() =
    "customerId " + thisSCPP.customerId.toString + ", startTime " + thisSCPP.startTime.toString + ", endTime " + thisSCPP.endTime.toString + ", volume " + thisSCPP.volume.toString
}
