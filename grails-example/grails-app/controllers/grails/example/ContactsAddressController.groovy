package grails.example


import grails.converters.JSON

class ContactsAddressController {
    ContactsAddressService contactsAddressService

    def addAddress(Long id) {
        def body = request.JSON
        def result = [:]

        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsAddressService.addAddress(id, body.address)
            render result as JSON
        } catch (Exception e) {
            response.status = 400
            render([error: 'BAD_REQUEST'] as JSON)
        }
    }

    def updateAddress(Long id) {
        def result = [:]

        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsAddressService.updateAddress(id, params.address)
            render result as JSON
        } catch (Exception e) {
            response.status = 400
            render([error: 'BAD_REQUEST'] as JSON)
        }
    }

    def deleteAddress(Long id) {
        def result = [:]
        if (!id) {
            response.status = 400
            render([error: 'ID_IS_MISSING'] as JSON)
        }
        try {
            result.response = contactsAddressService.deleteAddress(id)
            render result as JSON
        } catch (Exception e) {
            response.status = 500
            render([error: 'INTERNAL_SERVER_ERROR'] as JSON)
        }
    }
}
