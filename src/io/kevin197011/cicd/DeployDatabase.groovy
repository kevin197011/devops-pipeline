package io.kevin197011.cicd

@Grapes([
        @Grab(group = 'org.wisdom-framework', module = 'mysql-connector-java', version = '5.1.34_1'),
//        @GrabConfig( systemClassLoader=true )
])

import groovy.sql.Sql

class DeployDatabase {
    private String project
    private String host
    private String database
    private String username
    private String password

    DeployDatabase(String project, String host, String database, String username, String password) {
        this.project = project
        this.host = host
        this.database = database
        this.username = username
        this.password = password
    }

    boolean execute() {
        boolean val = false

        String sqlData = new File("/tmp/workspace/${this.project}/sql/t.sql").text

        Sql instance = Sql.newInstance("jdbc:mysql://" + this.host + ":3306/" + this.database + "?allowMultiQueries=true",
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
