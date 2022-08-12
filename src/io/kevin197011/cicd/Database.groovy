package io.kevin197011.cicd

@Grab('mysql:mysql-connector-java:5.1.6')
@GrabConfig(systemClassLoader=true)

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
        Sql instance = Sql.newInstance("jdbc:mysql://" + this.host + "?allowMultiQueries=true", this.username, this.password, "com.mysql.jdbc.Driver")
        boolean execute = instance.execute(sqlDate)
        instance.close()
        return execute
    }

}
