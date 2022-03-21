package daos

import grails.gorm.transactions.Transactional
import grails.example.Addresses
import grails.example.Contacts
import grails.example.Emails
import grails.example.PhoneNumbers

@Transactional
class ContactsDaoService {

    def saveContact(contactRequest) {
        Addresses address = new Addresses(address: contactRequest.address)
        Emails email = new Emails(email: contactRequest.email)
        PhoneNumbers phoneNumber = new PhoneNumbers(phoneNumber: contactRequest.phoneNumber)

        Contacts contacts = new Contacts(name: contactRequest.name, lastName: contactRequest.lastName)
                .addToAddresses(address).addToEmails(email).addToPhoneNumbers(phoneNumber).save(failOnError:true)
    }

    def getAll() {
        def contactsList = []
        def contacts = Contacts.getAll()

        contacts.each { contact ->
            contactsList.add(mappedObject(contact))
        }
        return contactsList
    }

    def getById(Long id) {
        return Contacts.get(id)
    }

    def getContactMappedById(Long id) {
        def contact = getById(id)

        if (contact) {
            return mappedObject(contact)
        }
    }

    def findWithQuery(query) {
        def contactsList = []
        def contacts = Contacts.createCriteria().list() {
            and {
                if (query.name) { like("name", "%${query.name}%")}
                if (query.lastName) { like("lastName", "%${query.lastName}%")}
                if (query.phoneNumber) {
                    phoneNumbers {
                        like("phoneNumber", "%${query.phoneNumber}%")
                    }
                }
                if (query.email) {
                    emails {
                        like("email", "%${query.email}%")
                    }
                }
                if (query.address) {
                    addresses {
                        like("address", "%${query.address}%")
                    }
                }
            }

        }

        contacts.each { contact ->
            contactsList.add(mappedObject(contact))
        }
        return contactsList
    }

    def paginatedList(query) {
        def contactsList = []
        def contacts = Contacts.list(query)

        contacts.each { contact ->
            contactsList.add(mappedObject(contact))
        }
        return contactsList
    }

    def delete(Long id) {
        def contact = getById(id)
        contact.delete()
    }

    def mappedObject(def object) {
        def result = [:]

        result.id = object.id
        result.name = object.name
        result.lastName = object.lastName
        result.phoneNumber = object.phoneNumbers.toArray()
        result.email = object.emails.toArray()
        result.address = object.addresses.toArray()

        return result
    }
}
