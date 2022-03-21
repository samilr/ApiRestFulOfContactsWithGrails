package grails.example

import daos.ContactsDaoService
import grails.gorm.transactions.Transactional

@Transactional
class ContactsPhoneNumberService {
    ContactsDaoService contactsDaoService

    def addPhoneNumber(Long id, phoneNumber) {
        def contact = contactsDaoService.getById(id)
        PhoneNumbers phoneNumbers = new PhoneNumbers(phoneNumber: phoneNumber)

        contact.addToPhoneNumbers(phoneNumbers).save(failOnError:true)
        return "PHONE_NUMBER_ADDED"
    }

    def updatePhoneNumber(Long id, newPhoneNumber) {
        def phoneNumber = PhoneNumbers.get(id);
        phoneNumber.phoneNumber = newPhoneNumber

        phoneNumber.save(failOnError:true)
        return "PHONE_NUMBER_UPDATED"
    }

    def deletePhoneNumber(Long id) {
        def phoneNumber = PhoneNumbers.get(id);

        phoneNumber.delete()
        return "PHONE_NUMBER_DELETED"
    }
}

