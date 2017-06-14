package com.tvi.app

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport

class MainScalatraServlet extends sampleScalatraBackofficeStack with JacksonJsonSupport {

  /**
    * HealthCheck
    * Is the service alive?
    */
  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  /**
    * Posts a new tariff
    */
  post("/tariff") {
    val tarif = parsedBody.extract[Tariff]
    val result = TariffManager.set(tarif)

    contentType = "text/html"
    jade(
      "/templates/views/tariff.ssp",
      "layout" -> "WEB-INF/templates/layouts/default.jade",
      "title" -> "POST tariff",
      "result" -> result.toString,
      "startFee" -> tarif.startFee,
      "hourlyFee" -> tarif.hourlyFee,
      "feePerKWh" -> tarif.feePerKWh,
      "activeStarting" -> tarif.activeStarting
    )
  }

  post("/scpp") {
    val scpp = parsedBody.extract[SCPP]
    val result = SCPPManager.add(scpp)

    contentType = "text/html"
    jade(
      "/templates/views/scpp.ssp",
      "layout" -> "WEB-INF/templates/layouts/default.jade",
      "title" -> "POST scpp",
      "result" -> result.toString,
      "customerId" -> scpp.customerId,
      "startTime" -> scpp.startTime,
      "endTime" -> scpp.endTime,
      "volume" -> scpp.volume
    )
  }

  get("/overview") {
    val result = SCPPManager.getOverview()

    contentType = "text/html"
    jade("/templates/views/overview.ssp",
         "layout" -> "WEB-INF/templates/layouts/default.jade",
         "title" -> "GET overview",
         "result" -> result)
  }

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit lazy val jsonFormats: Formats = DefaultFormats
}
