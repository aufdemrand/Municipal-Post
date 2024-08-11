package muni

import muni.documents.Municipality
import spaceport.communications.http.launchpad.Launchpad
import spaceport.computer.alerts.Alert
import spaceport.computer.alerts.results.HttpResult

// This file serves as the main router for the application, handling most of the
// application specific routing logic. Note: Some routing is specified in
// spaceport-utils/engineering/Router.groovy to handle generic routing logic,
// login, common assets, and other things.

class _Router {

    // Hitting the root just sends the user to the index page
    @Alert('on / hit')
    static _root(HttpResult r) {
        r.setRedirectUrl('/index.html')
    }

    // Launchpad is Spaceport's templating engine
    static def launchpad = new Launchpad()


    //
    // STANDARD ROUTES (UNAUTHENTICATED)

    // This serves as the main entry point for a root request and
    // shows an index page geared towards a 'guest' of the municipality
    @Alert('on /index.html hit')
    static _index(HttpResult r) {
        launchpad.assemble(['guests/index.ghtml']).launch(r, 'wrapper.ghtml')
    }


    //
    // AUTHENTICATED ROUTES

    // The authPlug will ensure that the user is authenticated
    // before continuing to the next route. If the user is not
    // authenticated, they will be redirected to the login page.
    static authPlug = { HttpResult r ->
        if (!r.client.isAuthenticated()) {
            def title = 'Login | ' + Municipality.get().getName()
            r.setRedirectUrl('/*/login/' +
                    '?title=' + title +
                    '&logo=/assets/img/municipal-logo.svg' +
                    '&redirect-url=' + r.context.request.requestURI)
            return false
        }
        return true
    }


    // UI for the Municipal Clerk, Municipal Administrator, Code Enforcement, Mayor, Elected Officials, etc.
    // This UI is a more back-end approach to Municipal operations, and allows people in specific roles
    // to access and create data that is not available to the general public.
    @Alert('~on /(clerk|administrator|mayor|code-enforcement|elected|businesses|contractors|employee)/(.*) hit')
    static _backend(HttpResult r) {
        def role = r.matches[0]
        if (r.authorize(authPlug) && r.client.clientDocument.hasPermission(role)) {
            def path = r.matches[1]
            launchpad.assemble([
                    "${ role }/_sidebar.ghtml",
                    "${ role }/${ path }.ghtml"
            ]).launch(r, 'backend-wrapper.ghtml')
        } else {
            r.setStatus(403) // Forbidden
        }
    }


}
