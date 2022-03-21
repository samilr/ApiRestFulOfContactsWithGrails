package grails.example


import grails.test.mixin.integration.Integration
import grails.transaction.*
import grails.web.mime.MimeType

import static grails.web.http.HttpHeaders.*
import static org.springframework.http.HttpStatus.*
import spock.lang.*
import geb.spock.*
import grails.plugins.rest.client.RestBuilder

@Integration
@Rollback
class DatabindingSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Test databinding"() {
        when:
            def resp = restBuilder().post("http://localhost:${serverPort}/article") {
                contentType(MimeType.JSON_API.name)
                json {
                    data = {
                        type = "photos"
                        attributes = {
                            title = "Foo"
                        }
                        relationships = {
                            author = {
                                data = {
                                    type = "author"
                                    id = 1
                                }
                            }
                        }
                    }
                }
            }

        then:"The response is correct"
            resp.status == CREATED.value()
            resp.headers[CONTENT_TYPE] == ['application/json;charset=UTF-8']
            resp.json.data.relationships.author.data.id == '1'
            resp.json.data.relationships.author.data.type == 'person'
            resp.json.data.attributes.title == 'Foo'
            resp.json.data.id == '3'
            resp.json.data.type == 'article'
            resp.json.links.related.href == '/people/1'
            resp.json.links.self == '/articles/3'
    }

    RestBuilder restBuilder() {
        new RestBuilder()
    }
}
