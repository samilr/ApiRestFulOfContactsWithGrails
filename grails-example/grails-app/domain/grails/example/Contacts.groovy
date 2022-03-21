package grails.example

class Contacts {

    Integer id
    String name
    String lastName

    Set<PhoneNumbers> phoneNumbers
    Set<Emails> emails
    Set<Addresses> addresses

    static hasMany = [phoneNumbers: PhoneNumbers, emails: Emails, addresses: Addresses]
//
    static constraints = {
        name(nullable: false, blank: false, matches: "[a-zA-Z]+")
        lastName(nullable: false, blank: false, matches: "[a-zA-Z]+")
    }
}
