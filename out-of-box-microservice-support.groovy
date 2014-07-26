@Grab('io.ratpack:ratpack-groovy-test:0.9.6')

import static ratpack.groovy.test.embed.EmbeddedApplications.embeddedApp
import static groovy.json.JsonOutput.toJson
import groovy.json.JsonSlurper

def slurper = new JsonSlurper()

def app = embeddedApp {
  handlers {
    get {
        response.send toJson([foo: "bar", bar: "baz"])
    }
  }
}

def json = "${app.address}".toURL().text
def obj = slurper.parseText(json)

assert obj.foo == "bar"
assert obj.bar == "baz"