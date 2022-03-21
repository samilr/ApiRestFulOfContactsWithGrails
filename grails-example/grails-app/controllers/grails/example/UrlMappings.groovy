package grails.example

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        // Contacts Controller
        get "/api/contacts"(controller: "contacts", action: "getAllContacts")
        get "/api/contacts/paginate"(controller: "contacts", action: "getPaginatedList")
        get "/api/contacts/filter"(controller: "contacts", action: "filterContacts")
        get "/api/contacts/$id?"(controller: "contacts", action: "getContactById")
        post "/api/contacts"(controller: "contacts", action: "createContact")
        delete "/api/contacts/$id?"(controller: "contacts", action: "deleteContact")
        patch "/api/contacts/$id?"(controller: "contacts", action: "updateContact")

        // Contacts Address Controller
        post "/api/contacts/address/$id?"(controller: "contactsAddress", action: "addAddress")
        patch "/api/contacts/address/$id?"(controller: "contactsAddress", action: "updateAddress")
        delete "/api/contacts/address/$id?"(controller: "contactsAddress", action: "deleteAddress")

        // Contacts Email Controller
        post "/api/contacts/email/$id?"(controller: "contactsEmail", action: "addEmail")
        patch "/api/contacts/email/$id?"(controller: "contactsEmail", action: "updateEmail")
        delete "/api/contacts/email/$id?"(controller: "contactsEmail", action: "deleteEmail")

        // Contacts PhoneNumber Controller
        post "/api/contacts/phone-number/$id?"(controller: "contactsPhoneNumber", action: "createPhoneNumber")
        patch "/api/contacts/phone-number/$id?"(controller: "contactsPhoneNumber", action: "updatePhoneNumber")
        delete "/api/contacts/phone-number/$id?"(controller: "contactsPhoneNumber", action: "deletePhoneNumber")


        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}