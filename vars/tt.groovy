#!/usr/bin/env groovy
// println(System.getProperty("java.ext.dirs"))

import io.kevin197011.cicd.Config
import io.kevin197011.cicd.Gitlab
import io.kevin197011.cicd.DeployDatabase

def call() {

    def project = Config.project
    def appName = Config.appName
    def appPath = Config.appPath
    def appConfig = Config.appConfig

    def database = new DeployDatabase('t1', 'localhost', 'tt', 'devops', '123456')
    def gitlab = new Gitlab(script: this)

    //pipeline
    pipeline {
        agent any

        options {
            timestamps()
            skipDefaultCheckout()
            disableConcurrentBuilds()
            timeout(time: 1, unit: 'HOURS')
        }

//        parameters {
//            choice(name: 'project', choices: project, description: 'Which project?')
//            choice(name: 'appName', choices: appName[params.project], description: 'Which appName?')
//            booleanParam(name: 'DeployDatabase', defaultValue: false, description: 'do?')
//            booleanParam(name: 'doDeploy', defaultValue: false, description: 'do?')
//        }


        stages {

            stage('Parameters') {
                steps {
                    script {
                        properties([
                                parameters([
                                        [$class      : 'ChoiceParameter',
                                         choiceType  : 'PT_SINGLE_SELECT',
                                         description : 'Select the Environemnt from the Dropdown List',
                                         filterLength: 1,
                                         filterable  : false,
                                         name        : 'Env',
                                         script      : [
                                                 $class        : 'GroovyScript',
                                                 fallbackScript: [
                                                         classpath: [],
                                                         sandbox  : false,
                                                         script   :
                                                                 "return['Could not get The environemnts']"
                                                 ],
                                                 script        : [
                                                         classpath: [],
                                                         sandbox  : false,
                                                         script   :
                                                                 "return['dev','stage','prod']"
                                                 ]
                                         ]
                                        ],
                                        [$class              : 'CascadeChoiceParameter',
                                         choiceType          : 'PT_SINGLE_SELECT',
                                         description         : 'Select the AMI from the Dropdown List',
                                         name                : 'AMI List',
                                         referencedParameters: 'Env',
                                         script              :
                                                 [$class        : 'GroovyScript',
                                                  fallbackScript: [
                                                          classpath: [],
                                                          sandbox  : false,
                                                          script   : "return['Could not get Environment from Env Param']"
                                                  ],
                                                  script        : [
                                                          classpath: [],
                                                          sandbox  : false,
                                                          script   : '''
                                                                if (Env.equals("dev")){
                                                                    return["ami-sd2345sd", "ami-asdf245sdf", "ami-asdf3245sd"]
                                                                }
                                                                else if(Env.equals("stage")){
                                                                    return["ami-sd34sdf", "ami-sdf345sdc", "ami-sdf34sdf"]
                                                                }
                                                                else if(Env.equals("prod")){
                                                                    return["ami-sdf34sdf", "ami-sdf34ds", "ami-sdf3sf3"]
                                                                }
                                                                '''
                                                  ]
                                                 ]
                                        ],
                                        [$class              : 'DynamicReferenceParameter',
                                         choiceType          : 'ET_ORDERED_LIST',
                                         description         : 'Select the  AMI based on the following information',
                                         name                : 'Image Information',
                                         referencedParameters: 'Env',
                                         script              :
                                                 [$class: 'GroovyScript',
                                                  script: 'return["Could not get AMi Information"]',
                                                  script: [
                                                          script: '''
                                                                if (Env.equals("dev")){
                                                                    return["ami-sd2345sd:  AMI with Java", "ami-asdf245sdf: AMI with Python", "ami-asdf3245sd: AMI with Groovy"]
                                                                }
                                                                else if(Env.equals("stage")){
                                                                    return["ami-sd34sdf:  AMI with Java", "ami-sdf345sdc: AMI with Python", "ami-sdf34sdf: AMI with Groovy"]
                                                                }
                                                                else if(Env.equals("prod")){
                                                                    return["ami-sdf34sdf:  AMI with Java", "ami-sdf34ds: AMI with Python", "ami-sdf3sf3: AMI with Groovy"]
                                                                }
                                                                '''
                                                  ]
                                                 ]
                                        ]
                                ])
                        ])
                    }
                }
            }

            stage('git clone item') {
                steps {
                    script {
                        gitlab.gitCloneItem('https://github.com/kevin197011/chatOps.git', 'master')
                    }
                }
            }

//            stage('deploy database') {
//                steps {
//                    script {
//                        if (params.DeployDatabase) {
//                            def val = database.execute()
//                            if (val) {
//                                println("sql execute succeed!")
//                            } else {
//                                error("sql execute error!")
//                            }
//                        } else {
//                            println("deploy database skip...")
//                        }
//                    }
//                }
//            }

//            stage('deploy app') {
//                steps {
//                    script {
//                        print("project => ${params.project}")
//                        print("appName => ${params.appName}")
//                    }
//                }
//            }

            stage('deploy config then restart') {
                steps {
                    script {
                        sleep(1)
                    }
                }
            }

            stage('check list') {
                steps {
                    script {
                        sleep(1)
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
