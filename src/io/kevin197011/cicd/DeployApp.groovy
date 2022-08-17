package io.kevin197011.cicd

class DeployApp {
    Script script

//    private String project
//    private String app
//    private String deployPath
//    private String host

//    void deploy(String project, String app, String host, String deployPath) {
    void deploy(String project, String app, String host) {
//        script.sh("rsync -avzR --delete /tmp/project/apps/${project}/${app}/ deploy@${host}:${deployPath}/")
//        script.sh("ssh deploy@${host} 'chown -R deploy:deploy ${deployPath}'")
//        script.echo("DeployApp deploy ${project} ${app} in ${host}!")
//        script.sh("bash -x /tmp/workspace/${project}/scripts/${app}_deploy_app.sh ${host}")
        script.sh("bash /tmp/workspace/${project}/scripts/${app}_deploy_app.sh ${host}")
    }

}
