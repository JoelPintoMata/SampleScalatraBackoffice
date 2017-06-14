package com.app

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class SCPPManagerSpec extends ScalatraSpec {
  def is =
    "The first scpp is inserted" ^
      "should be ok" ! addOk ^
      "Other scpp are inserted" ^
      "should be ok and the overview should be generated correctly" ! getOverview ^
      end

  val tariff1 = new Tariff(0.20, 2.00, 1.00, "2019-01-01T00:00:00.000Z")
  val scpp1 =
    new SCPP("john1", "2019-01-28T09:00:00", "2019-01-28T16:00:00", 30)
  def addOk = {
    TariffManager.set(tariff1)
    SCPPManager.add(scpp1) should beEqualTo(
      "SCPP added: " + scpp1.toString() + " fee 44.2")
  }

  val scpp2 =
    new SCPP("john3", "2019-01-28T09:00:00", "2019-01-28T16:00:00", 30)
  val scpp3 =
    new SCPP("john3", "2019-01-28T09:00:00", "2019-01-28T16:00:00", 30)
  val scpp4 =
    new SCPP("john2", "2019-01-28T09:00:00", "2019-01-28T16:00:00", 30)
  val scpp5 =
    new SCPP("john1", "2019-01-28T09:00:00", "2019-01-28T16:00:00", 30)
  def getOverview = {
    TariffManager.set(tariff1)
    SCPPManager.add(scpp1)
    SCPPManager.add(scpp2)
    SCPPManager.add(scpp3)
    SCPPManager.add(scpp4)
    SCPPManager.add(scpp5)
    SCPPManager.get().groupBy(_.customerId) should be size(3)
    SCPPManager.get().groupBy(_.customerId).get("john1").get should be size(2)
    SCPPManager.get().groupBy(_.customerId).get("john2").get should be size(1)
    SCPPManager.get().groupBy(_.customerId).get("john3").get should be size(2)
  }
}