package com.example

class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/oauth2/google/complete"(controller: 'googleOAuth2', action: 'complete')
        "/"(controller: 'dashboard', action: 'index')
        "500"(view:'/error')
        "404"(view:'/notFound')

    }
}
