package io.kevin197011.cicd


class Gitlab {

    Script script

    void gitCloneItem(String repo, String token, String branch = 'master') {
        script.echo("git clone chatOps!")
        script.sh "whereis git"
        script.git(url: "https://github.com/kevin197011/chatOps.git", branch: 'master')
    }
}
