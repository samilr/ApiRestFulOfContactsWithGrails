package grails.example


import grails.converters.JSON

class ContactsController {
    ContactsService contactsService

    def createContact() {
        def body = request.JSON
        def result = [:]
        try {
            result.response = contactsService.createContact(body)
            render result as JSON
        } catch (Exception e) {
            response.status = 400
            render([error: 'BAD_REQUEST'] as JSON)
        }
    }
    // faltan validadores
    def updateContact(Long id) {
        def result = [:]

        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsService.updateContact(id, params)
            render result as JSON
        } catch (Exception e) {
            response.status = 400
            render([error: 'BAD_REQUEST'] as JSON)
        }
    }

    def getAllContacts() {
        def result = [:]
        result.response = contactsService.getAllContacts()
        if (!result.response || result.response.isEmpty()) {
            response.status = 404
            render([error: 'CONTACTS_NOT_FOUND'] as JSON)
        }
        render result as JSON
    }

    def getPaginatedList() {
        def result = [:]
        result.response = contactsService.getPaginatedList(params)
        if (!result.response || result.response.isEmpty()) {
            response.status = 404
            render([error: 'CONTACTS_NOT_FOUND'] as JSON)
        }
        render result as JSON
    }

    def filterContacts() {
        def result = [:]
        result.response = contactsService.filterContacts(params)
        if (!result.response || result.response.isEmpty()) {
            response.status = 404
            render([error: 'CONTACTS_NOT_FOUND'] as JSON)
        }
        render result as JSON
    }


    def getContactById(Long id) {
        def result = [:]

        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        result.response = contactsService.getContactById(id)
        if (!result.response) {
            response.status = 404
            render([error: 'CONTACT_NOT_FOUND'] as JSON)
        }
        render result as JSON

    }

    def deleteContact(Long id) {
        def result = [:]
        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsService.deleteContact(id)
            render result as JSON
        } catch (Exception e) {
            response.status = 500
            render([error: 'INTERNAL_SERVER_ERROR'] as JSON)
        }
    }
}
