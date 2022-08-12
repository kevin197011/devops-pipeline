package io.kevin197011.cicd

class Gitlab {

    static void gitCloneItem(String repo, String token, String branch = 'master') {

        def result = new StringBuffer()
        def error = new StringBuffer()

        def cmd = "git clone ${repo}".execute()
        cmd.consumeProcessOutput(result, error)
        cmd.waitForOrKill(1000)

        if (!error.toString().equals(""))
            println "Error al ejecutar el comando"
        else {
            println "Ejecutado correctamente"
            println result
        }
    }
}
