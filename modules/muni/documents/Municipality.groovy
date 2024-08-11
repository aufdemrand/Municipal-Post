package muni.documents

import spaceport.computer.memory.physical.Document
import spaceport.computer.memory.virtual.Cargo


/*
 * The Municipality document is a representation of the municipality
 * that Municipal Post is managing. It contains all of the basic
 * information about the municipality, such as the name, state, county,
 * population, year founded, etc.
 *
 */

class Municipality extends Document {

    // Gets the Document from the database. There is only one 'municipality'.
    //
    static Municipality get() {
        return getAs(Municipality, 'municipality', 'municipal-post')
    }


    //
    // Instance methods


    // NAME of the Municipality will be used throughout the application
    //
    def getName() { fields.name ?: '—' }
    def setName(String name) {
        fields.name = name.clean()
        save()
    }

    // STATE that the Municipality is located in
    //
    def getState() { fields.state ?: '—' }
    def setState(String state) {
        fields.state = state.clean()
        save()
    }

    // COUNTY that the Municipality is located in
    //
    def getCounty() { fields.county ?: '—' }
    def setCounty(String county) {
        fields.county = county.clean()
        save()
    }

    // Description of the Municipality is used in the 'Discover' section
    //
    def getDescription() { fields.description ?: '—' }
    def setDescription(String description) {
        description.cleanType = 'relaxed'
        fields.description = description.clean()
        save()
    }

}
