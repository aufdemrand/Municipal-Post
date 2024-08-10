package muni

import spaceport.Spaceport
import spaceport.computer.alerts.Alert
import spaceport.computer.alerts.Result

class MunicipalPost {

    @Alert('on initialize')
    static _init(Result r) {

        // Initialize database
        if (!Spaceport.main_memory_core.containsDatabase('municipal-post')) {
            Spaceport.main_memory_core.createDatabase('municipal-post')
        }

    }

}