package grails.example


import grails.converters.JSON

class ContactsPhoneNumberController {
    ContactsPhoneNumberService contactsPhoneNumberService

    def createPhoneNumber(Long id) {
        def body = request.JSON
        def result = [:]

        if (!id) {
            response.status = 404
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsPhoneNumberService.addPhoneNumber(id, body.phoneNumber)
            render result as JSON
        } catch (Exception e) {
            response.status = 400
            render([error: 'BAD_REQUEST'] as JSON)
        }
    }

    def updatePhoneNumber(Long id) {
        def result = [:]

        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsPhoneNumberService.updatePhoneNumber(id, params.phoneNumber)
            render result as JSON
        } catch (Exception e) {
            response.status = 400
            render([error: 'BAD_REQUEST'] as JSON)
        }
    }

    def deletePhoneNumber(Long id) {
        def result = [:]
        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsPhoneNumberService.deletePhoneNumber(id)
            render result as JSON
        } catch (Exception e) {
            response.status = 500
            render([error: 'INTERNAL_SERVER_ERROR'] as JSON)
        }
    }
}
