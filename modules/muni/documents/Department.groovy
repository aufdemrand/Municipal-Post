package muni.documents

import spaceport.computer.memory.physical.Document
import spaceport.computer.memory.physical.View

/*
 * A Department is a Document in the database that represents a
 * specific department within the municipality. This document
 * contains all of the basic information about the department.
 *
 */

class Department extends Document {

    // Grabs a View of all of the departments in the database
    static View all() {
        if (cached != null) return cached
        return View.get('views', 'all-departments', 'municipal-post')
    }

    private static cached = null

    static Department byId(String id) {
        return getAs(Department, id, 'municipal-post')
    }

    static Department create(String name) {
        String id = 6.randomID()

        return getAs(Department, id, 'municipal-post').tap {
            fields.name = name.clean()
            type = 'Department'
            save()
            // Clear cache
            cached = null
        }
    }






}

