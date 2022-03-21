package grails.example


import daos.ContactsDaoService
import grails.gorm.transactions.Transactional

@Transactional
class ContactsEmailsService {
    ContactsDaoService contactsDaoService

    def addEmail(Long id, email) {
        def contact = contactsDaoService.getById(id)
        Emails emails = new Emails(email: email)

        contact.addToEmails(emails).save(failOnError:true)
        return "EMAIL_ADDED"
    }

    def updateEmail(Long id, newEmail) {
        def email = Emails.get(id);
        email.email = newEmail

        email.save(failOnError:true)
        return "EMAIL_UPDATED"
    }

    def deleteEmail(Long id) {
        def email = Emails.get(id);

        email.delete()
        return "EMAIL_DELETED"
    }
}
