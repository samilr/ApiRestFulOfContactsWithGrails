package grails.example

class Addresses {

    Integer id
    String address

    static belongsTo = [contact: Contacts]
    static constraints = {
        address(nullable: true, blank: true)
    }
}