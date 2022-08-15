package io.kevin197011.cicd


class Gitlab {

    Script script

    void gitCloneItem(String repo, String branch = 'master') {

        Object repoName = repo.split('/').last().toString().tokenize('.').first().toString()
        script.echo("git clone ${repoName}!")

        Object appsDir = new File('/tmp/apps')
        if (!appsDir.exists()) {
            appsDir.mkdirs()
        }

        Object repoDir = new File("/tmp/apps/${repoName}")
        if (repoDir.exists()) {
            repoDir.deleteDir()
        }

        script.sh("git clone --branch ${branch} ${repo} /tmp/apps/${repoName}")
    }
}
