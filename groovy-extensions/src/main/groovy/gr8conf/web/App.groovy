package gr8conf.web

import gr8conf.meta.annotations.BootApp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.client.RestTemplate
import ratpack.test.embed.EmbeddedApplication


import static groovy.json.JsonOutput.toJson
import static ratpack.groovy.test.embed.EmbeddedApplications.embeddedApp
import static org.springframework.boot.SpringApplication.run

@BootApp("/foo")
class App {

  @Bean
  RestTemplate r() {
    new RestTemplate()
  }

  @Bean
  EmbeddedApplication mockApp() {
    embeddedApp {
      handlers {
        get {
          render toJson([foo: "bar", bar: "baz"])
        }
      }
    }
  }

  @Autowired
  RestTemplate r

  @Autowired
  EmbeddedApplication mockApp

  @RequestMapping(method = RequestMethod.GET)
  def get() {
    def responseFromMockApi = r.getForObject(mockApp.address, String)

    responseFromMockApi
  }

  static void main(_) {
    run this, [] as String[]
  }
}
