package io.kevin197011.cicd

class DeployConfig {

    Script script

//    void restart(String host) {
//
//        script.sh("ssh deploy@${host} 'bash /opt/script/app_restart.sh'")
//
//    }

//    void deploy(String project, String appName,String host, Map appConfig) {
    void deploy(String project, String appName,String host) {

//        appConfig.each {
//            script.sh("scp -vr /tmp/project/config/${project}/${appName}/config/${it.key} deploy@${host}:${it.value}")
//        }
        script.echo("DeployApp deploy ${project} ${appName} in ${host}!")
//        script.sh("bash /tmp/workspace/${project}/${appName}/scripts/deploy_config.sh ${host}")

//        this.restart(host)
    }
}
