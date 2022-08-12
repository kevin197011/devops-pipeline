package io.kevin197011.cicd

class Gitlab {

    static boolean gitCloneItem(String repo, String token, String branch = 'master') {
//        git(url: repo,
//                credentialsId: token,
//                branch: branch)

        sh("git clone ${repo}" )

}
