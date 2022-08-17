package io.kevin197011.cicd

class DeployConfig {

    Script script

//    void restart(String host) {
//
//        script.sh("ssh deploy@${host} 'bash /opt/script/app_restart.sh'")
//
//    }

//    void deploy(String project, String app,String host, Map config) {
    void deploy(String project, String app,String host) {

//        appConfig.each {
//            script.sh("scp -vr /tmp/project/config/${project}/${app}/config/${it.key} deploy@${host}:${it.value}")
//        }
//        script.echo("DeployApp deploy ${project} ${app} config in ${host}!")
//        script.sh("bash /tmp/workspace/${project}/${app}/scripts/deploy_config.sh ${host}")
        script.sh("bash /tmp/workspace/${project}/scripts/${app}_deploy_config.sh ${host}")

//        this.restart(host)
    }
}
