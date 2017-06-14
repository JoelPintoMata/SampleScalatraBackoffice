import org.scalatra._
import javax.servlet.ServletContext

import com.app.MainScalatraServlet

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new MainScalatraServlet, "/*")
  }
}