

import net.sf.jsqlparser.parser.CCJSqlParserManager

afterMethodCall { mc ->
  def receiver = mc.receiver
  if (!isVariableExpression(receiver)) return

  def method = getTargetMethod(mc)

  if (classNodeFor(groovy.sql.Sql) == getType(receiver) && method.name == 'eachRow') {
    def argList = getArguments(mc)
    if (argList && isConstantExpression(argList[0])) {
      def pm = new CCJSqlParserManager();
      def sqlQuery = argList[0].text

      try {
        pm.parse(new StringReader(sqlQuery))
      } catch (e) {
        addStaticTypeError("SQL query is not valid: " + e, argList[0])
      }
    }
  }
}

