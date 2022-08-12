package io.kevin197011.cicd

@Grapes(
        @Grab(group = 'org.wisdom-framework', module = 'mysql-connector-java', version = '5.1.34_1')
)

import groovy.sql.Sql

class Database {
    private String host
    private String database
    private String username
    private String password

    Database(String host, String database, String username, String password) {
        this.host = host
        this.database = database
        this.username = username
        this.password = password
//        this.sqlData = sqlData
    }

    boolean execute() {
        boolean val = false

        def sqlData = new File("/tmp/sql/t1/t.sql").text

        def instance = Sql.newInstance("jdbc:mysql://" + this.host + ":3306/" + this.database + "?allowMultiQueries=true",
                this.username, this.password, "com.mysql.jdbc.Driver")
        instance.connection.autoCommit = false

        try {
            instance.execute(sqlData)
            instance.commit()
            val = true
        } catch (Exception ex) {
            instance.rollback()
            ex.printStackTrace()
        }
        instance.close()
        return val
    }

}
