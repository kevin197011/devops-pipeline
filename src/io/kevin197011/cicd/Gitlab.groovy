package io.kevin197011.cicd


class Gitlab {

    Script script

    void gitCloneItem(String repo, String token, String branch = 'master') {
        script.echo("git clone chatOps!")
        script.sh('[[ -d /tmp/apps || mkdir /tmp/apps ]]')
        script.sh("git clone https://github.com/kevin197011/chatOps.git /tmp/apps/chatOps")
//        script.git(url: "https://github.com/kevin197011/chatOps.git", branch: 'master')
    }
}
