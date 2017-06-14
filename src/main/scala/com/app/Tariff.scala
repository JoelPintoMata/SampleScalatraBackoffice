package com.app

/**
  * Service tarif case class
  * @param startFee A fee that applies one time to every transaction
  * @param hourlyFee A fee for the time that the car occupied the charger
  * @param feePerKWh A fee for the energy that the car consumed
  * @param activeStarting The moment from which this tariff is active
  */
case class Tariff(startFee: BigDecimal,
                  hourlyFee: BigDecimal,
                  feePerKWh: BigDecimal,
                  activeStarting: String) extends TariffToString

/**
  * Custom toString method
  */
trait TariffToString { thisTarif:Tariff =>
  override def toString() = "startFeed " + thisTarif.startFee.toString + ", hourlyFee " + thisTarif.hourlyFee.toString + ", feePerKWh " + thisTarif.feePerKWh.toString + ", activeStarting " + thisTarif.activeStarting.toString
}