package grails.example


import grails.converters.JSON

class ContactsEmailController {
    ContactsEmailsService contactsEmailsService

    def addEmail(Long id) {
        def body = request.JSON
        def result = [:]

        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsEmailsService.addEmail(id, body.email)
            render result as JSON
        } catch (Exception e) {
            response.status = 400
            render([error: 'BAD_REQUEST'] as JSON)
        }
    }

    def updateEmail(Long id) {
        def result = [:]

        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsEmailsService.updateEmail(id, params.email)
            render result as JSON
        } catch (Exception e) {
            response.status = 400
            render([error: 'BAD_REQUEST'] as JSON)
        }
    }

    def deleteEmail(Long id) {
        def result = [:]
        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsEmailsService.deleteEmail(id)
            render result as JSON
        } catch (Exception e) {
            response.status = 500
            render([error: 'INTERNAL_SERVER_ERROR'] as JSON)
        }
    }
}
