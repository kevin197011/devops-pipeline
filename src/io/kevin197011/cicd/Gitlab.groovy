package io.kevin197011.cicd


class Gitlab {

    Script script

    void gitCloneItem(String repo, String token, String branch = 'master') {
        script.each("git clone chatOps!")
        script.git(url: "https://github.com/kevin197011/chatOps.git", branch: 'master')
    }
}
