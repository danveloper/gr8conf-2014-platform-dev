
@RestController
class App {
  @RequestMapping(method = RequestMethod.GET)
  def hello() {
    [msg: "hello"]
  }
}
