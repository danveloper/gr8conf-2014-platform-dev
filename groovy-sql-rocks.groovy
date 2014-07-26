@GrabConfig(systemClassLoader=true)
@Grab('com.h2database:h2:1.4.180')

import groovy.sql.Sql

def db = [url:'jdbc:h2:mem:', user:'sa', password:'', driver:'org.h2.Driver']
def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)

sql.execute "create table PROJECT (id int, value text)"
sql.execute "insert into PROJECT values(:id, :value)", [id: 1, value: "foo"]

println sql.rows("select * from project")
