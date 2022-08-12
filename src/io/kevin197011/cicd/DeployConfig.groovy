package io.kevin197011.cicd

class DeployConfig {

    Script script

    void restart(String host) {

        script.sh("ssh deploy@${host} 'bash /opt/script/app_restart.sh'")

    }

    void deploy(String project, String appName,String host, Map appConfig) {

        appConfig.each {
            script.sh("scp -vr /tmp/project/config/${project}/${appName}/config/${it.key} deploy@${host}:${it.value}")
        }
        this.restart(host)
    }
}
