package io.kevin197011.cicd


class Gitlab {

    Script script

    void gitCloneItem(String repo, String token, String branch = 'master') {

        def result = new StringBuffer()
        def error = new StringBuffer()

        def cmd = "git clone ${repo} /tmp/test".execute()
        cmd.consumeProcessOutput(result, error)
        cmd.waitForOrKill(1000)

        if (!error.toString().equals("")) {
            script.println("Error!")
        } else {
            script.println(result)
        }
    }
}
