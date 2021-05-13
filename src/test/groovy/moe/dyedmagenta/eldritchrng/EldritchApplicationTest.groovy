package moe.dyedmagenta.eldritchrng

import spock.lang.Specification

class EldritchApplicationTest extends Specification {


    def "should add 2"() {
        given:
            def a = 12
        when:
            def response = a + 2
        then:
            response == 14
    }
}
