package io.kevin197011.cicd

class Gitlab {

    static boolean gitCloneItem(script, String repo, String token, String branch = 'master') {
//        git(url: repo,
//                credentialsId: token,
//                branch: branch)

        script.sh("git clone ${repo}")
    }
}
