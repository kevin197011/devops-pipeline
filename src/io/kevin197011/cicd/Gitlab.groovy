package io.kevin197011.cicd

class Gitlab implements Serializable {

    static boolean gitCloneItem(String repo, String token, String branch = 'master') {
//        git(url: repo,
//                credentialsId: token,
//                branch: branch)

        pipeline.sh("git clone ${repo}")
    }
}
