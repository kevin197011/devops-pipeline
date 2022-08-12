package io.kevin197011.cicd


class Gitlab {

    Script script

    void gitCloneItem(String repo, String branch = 'master') {
        script.echo("git clone chatOps!")

        def appsDir = new File('/tmp/apps')
        if (!appsDir.exists()) {
            appsDir.mkdirs()
        }
        script.sh("git clone --branch ${branch} ${repo} /tmp/apps/")
    }
}
