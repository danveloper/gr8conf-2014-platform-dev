package gr8conf

import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked(extensions = ['SqlTypeCheckingExt.groovy'])
class SqlTest {

  static void main(_) {
    def db = [url:'jdbc:hsqldb:mem:testDB', user:'sa', password:'', driver:'org.hsqldb.jdbc.JDBCDriver']
    def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)

    sql.eachRow('select * from PROJECT') { row ->
      println row
    }
  }
}