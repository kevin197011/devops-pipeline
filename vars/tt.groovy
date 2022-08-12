#!/usr/bin/env groovy

import io.kevin197011.cicd.Database

def call() {

    def sqlData = '''
    CREATE TABLE Author (
    id          INTEGER GENERATED,
    firstname   VARCHAR(64),
    lastname    VARCHAR(64)
  );
'''

    def database = new Database('localhost', 'devops', '123456', sqlData)

    //pipeline
    pipeline {
        agent any

        options {
            timestamps()
            skipDefaultCheckout()
            disableConcurrentBuilds()
            timeout(time: 1, unit: 'HOURS')
        }

        stages {
            stage('test') {
                steps {
                    script {
//                        println(System.getProperty("java.ext.dirs"))
                        def execute = database.execute()
                        if (execute) {
                            println("sql execute sucessed!")
                        } else {
                            error("sql execute error!")
                        }
                    }
                }
            }

            stage('parallel') {
                parallel {
                    stage('A') {
                        steps {
                            script {
                                sleep(10)
                            }
                        }
                    }
                    stage('B') {
                        steps {
                            script {
                                sleep(10)
                            }
                        }
                    }
                }
            }
        }

        post {
            always {
                script {
                    println('always')
                }
            }

            success {
                script {
                    println('success')
                }
            }

            failure {
                script {
                    println('failure')
                }
            }

            aborted {
                script {
                    println('aborted')
                }
            }
        }
    }
}
