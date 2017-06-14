package com.tvi.app

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class TariffManagerSpec extends ScalatraSpec {
  def is =
    "The first tariff is inserted" ^
      "should not be ok because its starts before the current time" ! setNotOk ^
      "Another tariff is inserted" ^
      "should be ok because its starts after the current time" ! setOk ^
      "Another tariff is inserted" ^
      "should not be ok because its starts before the current tariff start time" ! setBeforeCurrentNotOk ^
      "Another tariff is inserted" ^
      "should be ok because its starts after the current tariff start time" ! setAfterCurrentOk ^
      end

  val tariff1 = new Tariff(0.20, 1.00, .025, "2017-01-01T00:00:00.000Z")
  def setNotOk =
    TariffManager.set(tariff1) should beEqualTo(
      "Tariff not set: " + tariff1.toString)

  val tariff2 = new Tariff(0.20, 1.00, .025, "2019-01-01T00:00:00.000Z")
  def setOk = TariffManager.set(tariff2) should not be empty

  val tariff3 = new Tariff(0.20, 1.00, .025, "2018-01-01T00:00:00.000Z")
  def setBeforeCurrentNotOk =
    TariffManager.set(tariff3) should beEqualTo(
      "Tariff not set: " + tariff3.toString)

  val tariff4 = new Tariff(0.20, 1.00, .025, "2020-01-01T00:00:00.000Z")
  def setAfterCurrentOk =
    TariffManager.set(tariff4) should beEqualTo(
      "New tariff set: " + tariff4.toString)
}
