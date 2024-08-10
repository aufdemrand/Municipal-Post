package muni.documents

import spaceport.computer.memory.physical.Document
import spaceport.computer.memory.virtual.Cargo

class Municipality extends Document {

    // Gets the Document from the database with all of the basic
    // municipality information
    //
    static Municipality get() {
        return getAs(Municipality, 'municipality', 'municipal-post')
    }


    // Use Cargo to manipulate the document data — auto-save, reactivity, etc.

    def cargo = Cargo.fromDocument(this)

    // NAME of the Municipality will be used throughout the application
    //
    def getName() { cargo.getOrDefault('name', '—') }
    def setName(String name) { cargo.set('name', name.clean()) }

    // STATE that the Municipality is located in
    //
    def getState() { cargo.getOrDefault('state', '—') }
    def setState(String state) { cargo.set('state', state.clean()) }

    // COUNTY that the Municipality is located in
    //
    def getCounty() { cargo.getOrDefault('county', '—') }
    def setCounty(String county) { cargo.set('county', county.clean()) }


}
