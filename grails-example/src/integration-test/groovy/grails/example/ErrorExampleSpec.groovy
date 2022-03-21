package grails.example


import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Specification

@Integration
class ErrorExampleSpec extends Specification {

        @Shared
        @Value('${local.server.port}')
        int serverPort

        @Shared
        def rest = new RestBuilder()

        void "test error rendering with a simple forced error"() {
            when: 'an article with an error is rendered'
                def resp = rest.get("http://localhost:${serverPort}/forced/validationError") {
                    accept "application/json"
                }
                def contentType = resp.headers.getContentType()

            then: 'the response attributes are as expected'
                resp.status == HttpStatus.OK.value()
                contentType.subtype == 'json'
                contentType.type == 'application'

            and: 'the errors are present'
                resp.json.size() == 1
                resp.json.errors.size() == 2

            and: 'the first error is due to a blank title'
                resp.json.errors[0].code == "blank"
                resp.json.errors[0].source.field == "title"
                resp.json.errors[0].source.rejectedValue == ""

            and: 'the second error is due to a null author'
                resp.json.errors[1].code == "nullable"
                resp.json.errors[1].source.field == "author"
                resp.json.errors[1].source.rejectedValue == null
        }
    }

