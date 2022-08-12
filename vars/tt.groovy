#!/usr/bin/env groovy


import io.kevin197011.cicd.Gitlab
import io.kevin197011.cicd.Database

def call() {

    def sqlData = '''
CREATE TABLE IF NOT EXISTS `runoob_tbl`(
   `runoob_id` INT UNSIGNED AUTO_INCREMENT,
   `runoob_title` VARCHAR(100) NOT NULL,
   `runoob_author` VARCHAR(40) NOT NULL,
   `submission_date` DATE,
   PRIMARY KEY ( `runoob_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
'''

    def database = new Database('localhost', 'tt', 'devops', '123456', sqlData)
    def gitlab = new Gitlab()

    def repoList = [
            'a',
            'b',
            'c',
            'd'
    ]

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

            stage('git clone item') {
                steps {
                    script {
                        repoList.each {
                            println("git clone item: [${it}]")
                        }

                        gitlab.gitCloneItem('https://github.com/jenkinsci/file-parameters-plugin.git', 'test', 'master')
                    }
                }
            }

//            stage('deploy database') {
//                steps {
//                    script {
////                      println(System.getProperty("java.ext.dirs"))
//                        def val = database.execute()
//                        if (val) {
//                            println("sql execute succeed!")
//                        } else {
//                            error("sql execute error!")
//                        }
//                    }
//                }
//            }

            stage('deploy app') {
                steps {
                    script {
                        sleep(10)
                    }
                }
            }

            stage('deploy config') {
                steps {
                    script {
                        sleep(10)
                    }
                }
            }

            stage('restart app') {
                steps {
                    script {
                        sleep(10)
                    }
                }
            }

            stage('check list') {
                steps {
                    script {
                        sleep(10)
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
