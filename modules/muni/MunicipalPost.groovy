package muni

import spaceport.Spaceport
import spaceport.bridge.Command
import spaceport.computer.alerts.Alert
import spaceport.computer.alerts.Result

class MunicipalPost {

    @Alert('on initialize')
    static _init(Result r) {

        Command.println('MunicipalPost initializing...')

        // Initialize database
        if (!Spaceport.main_memory_core.containsDatabase('municipal-post')) {
            Spaceport.main_memory_core.createDatabase('municipal-post')
        }

    }


}