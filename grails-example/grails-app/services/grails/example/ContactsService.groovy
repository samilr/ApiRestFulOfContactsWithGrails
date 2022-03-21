package grails.example


import daos.ContactsDaoService
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import javassist.tools.web.BadHttpRequest
import org.springframework.web.client.HttpClientErrorException

@Transactional
class ContactsService {
    ContactsDaoService contactsDaoService

    def createContact(contactRequest) {
        contactsDaoService.saveContact(contactRequest)
        return "CONTACT_CREATED"
    }

    def getAllContacts() {
        return contactsDaoService.getAll()
    }

    def getPaginatedList(query) {
        return contactsDaoService.paginatedList(query)
    }

    def getContactById(Long id) {
        return contactsDaoService.getContactMappedById(id)
    }

    def filterContacts(query) {
        return contactsDaoService.findWithQuery(query)
    }

    def deleteContact(Long id) {
        contactsDaoService.delete(id)
        return "CONTACT_DELETED"
    }

    def updateContact(Long id, query) {
        def contact = contactsDaoService.getById(id)

        if (query.name != null && contact.name != query.name) {
            contact.name = query.name
        }
        if (query.lastName != null && contact.lastName != query.lastName) {
            contact.lastName = query.lastName
        }
        contact.save(failOnError:true)
        return "CONTACT_UPDATED"
    }
}
