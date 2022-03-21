package grails.example

class PhoneNumbers {

    Integer id
    String phoneNumber
    static belongsTo = [contact: Contacts]

    static constraints = {
        phoneNumber(nullable: false, blank: false, maxSize: 10)
    }
}
