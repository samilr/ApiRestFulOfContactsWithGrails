package grails.example

import grails.example.Article

import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Specification

@Rollback
@Integration
class ArticleSpec extends Specification {
    @Shared
    @Value('${local.server.port}')
    int serverPort

    @Shared
    RestBuilder rest = new RestBuilder()

    void "Test article rendering"() {
        given:
            Person gump = new Person(name: "Mr. Gump")
            gump.save(failOnError: true)
            Article article = new Article(
                author: gump,
                title: 'Save the Gumbo'
            )
            article.save(failOnError: true)

        when: 'an article with an error is rendered'
            def resp = rest.get("http://localhost:${serverPort}/articles/1") {
                accept "application/json"
            }
            def contentType = resp.headers.getContentType()

        then: 'the response attributes are as expected'
            resp.status == HttpStatus.OK.value()
            contentType.subtype == 'json'
            contentType.type == 'application'

        and: 'the links are present'
            resp.json.links.self == "/articles/1"
            resp.json.links.related.href == "/people/1"
    }
}
