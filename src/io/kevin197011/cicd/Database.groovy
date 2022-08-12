package io.kevin197011.cicd

@Grapes(
        @Grab(group='org.wisdom-framework', module='mysql-connector-java', version='5.1.34_1')
)

import java.sql.*;
import groovy.sql.Sql

class Database {
    private String host
    private String username
    private String password
    private String sqlData

    Database(String host, String username, String password, String sqlData) {
        this.host = host
        this.username = username
        this.password = password
        this.sqlData = sqlData
    }

    boolean execute() {
        Sql instance = Sql.newInstance("jdbc:mysql://" + this.host + ":3306/tt/?allowMultiQueries=true", this.username, this.password, "com.mysql.jdbc.Driver")
        boolean execute = instance.execute(sqlDate)
        instance.close()
        return execute
    }

}
