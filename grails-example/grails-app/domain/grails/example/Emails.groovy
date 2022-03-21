package grails.example

class Emails {

    Integer id
    String email
    static belongsTo = [contact: Contacts]
    static constraints = {
        email(nullable: true, blank: true, email: true)
    }
}
