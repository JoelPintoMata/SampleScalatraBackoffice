package com.app

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
    val tariff = new Tariff(params("startFee").asInstanceOf[BigDecimal], params("hourlyFee").asInstanceOf[BigDecimal], params("feePerKWh").asInstanceOf[BigDecimal], params("activeStarting"));
    val result = TariffManager.set(tariff)

    contentType = "text/html"
    jade(
      "/templates/views/tariff.ssp",
      "layout" -> "WEB-INF/templates/layouts/default.jade",
      "title" -> "POST tariff",
      "result" -> result.toString,
      "startFee" -> tariff.startFee,
      "hourlyFee" -> tariff.hourlyFee,
      "feePerKWh" -> tariff.feePerKWh,
      "activeStarting" -> tariff.activeStarting
    )
  }

  post("/scpp") {
    val scpp = new SCPP(params("customerId"), params("startTime"), params("endTime"), params("volume").asInstanceOf[BigDecimal]);
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
