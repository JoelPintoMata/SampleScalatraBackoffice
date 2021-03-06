package com.app

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class MainScalatraServletSpec extends ScalatraSpec {
  def is =
    "GET / on MainScalatraServlet" ^
      "should return status 200" ! root200 ^
      "GET /overview on MainScalatraServlet" ^
      "should return status 200" ! getOverview200 ^
      end

  addServlet(classOf[MainScalatraServlet], "/*")

  def root200 = get("/") {
    status must_== 200
  }

  def getOverview200 = get("/overview") {
    status must_== 200
  }
}