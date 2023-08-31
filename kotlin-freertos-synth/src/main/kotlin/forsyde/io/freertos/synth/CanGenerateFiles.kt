package forsyde.io.freertos.synth

import java.nio.file.Path

interface CanGenerateFiles {

    fun getFilesAndContent(): Map<Path, String> = HashMap()
}