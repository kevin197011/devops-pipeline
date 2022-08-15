package io.kevin197011.cicd

class DeployApp {
    Script script

//    private String project
//    private String appName
//    private String deployPath
//    private String host

//    void deploy(String project, String appName, String host, String deploypath) {
    void deploy(String project, String appName, String host) {
//        script.sh("rsync -avzR --delete /tmp/project/apps/${project}/${appName}/ deploy@${host}:${deploypath}/")
//        script.sh("ssh deploy@${host} chown -R deploy:deploy ${deploypath}")
//        script.echo("DeployApp deploy ${project} ${appName} in ${host}!")
//        script.sh("bash -x /tmp/workspace/${project}/scripts/${appName}_deploy_app.sh ${host}")
        script.sh("bash /tmp/workspace/${project}/scripts/${appName}_deploy_app.sh ${host}")
    }

}
