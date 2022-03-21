package grails.example

import daos.ContactsDaoService
import grails.gorm.transactions.Transactional

@Transactional
class ContactsAddressService {
    ContactsDaoService contactsDaoService

    def addAddress(Long id, address) {
        def contact = contactsDaoService.getById(id)
        Addresses addresses = new Addresses(address: address)

        contact.addToAddresses(addresses).save(failOnError:true)
        return "ADDRESS_ADDED"
    }

    def updateAddress(Long id, newAddress) {
        def address = Addresses.get(id);
        address.address = newAddress

        address.save(failOnError:true)
        return "ADDRESS_UPDATED"
    }

    def deleteAddress(Long id) {
        def address = Addresses.get(id);

        address.delete()
        return "ADDRESS_DELETED"
    }
}
