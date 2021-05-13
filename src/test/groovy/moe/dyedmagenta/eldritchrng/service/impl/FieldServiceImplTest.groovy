package moe.dyedmagenta.eldritchrng.service.impl

import moe.dyedmagenta.eldritchrng.dto.FieldType
import moe.dyedmagenta.eldritchrng.dto.GenericFieldDTO
import moe.dyedmagenta.eldritchrng.error.NotFoundException
import moe.dyedmagenta.eldritchrng.model.Field
import moe.dyedmagenta.eldritchrng.repository.FieldImageRepository
import moe.dyedmagenta.eldritchrng.repository.FieldRepository
import moe.dyedmagenta.eldritchrng.service.FieldService
import moe.dyedmagenta.eldritchrng.validation.FieldValidator
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import spock.lang.Specification

class FieldServiceImplTest extends Specification {

    def repository = Mock(FieldRepository)
    FieldImageRepository imageRepository = Mock()
    FieldValidator fieldValidator = Mock()

    FieldService service = new FieldServiceImpl(repository, imageRepository, fieldValidator)

    def "should create field"() {
        given:
            def dto = new GenericFieldDTO()
            dto.name = "14"
            dto.fieldType = FieldType.CITY
            def expectedFieldId = UUID.randomUUID()
        when:
            def response = service.createField(dto)

        then:
            1 * repository.save(_ as Field) >> { Field it ->
                it.id = expectedFieldId
                it
            }
            response.id == expectedFieldId
            response.name == dto.name
            response.iconImageId == null
    }

    def "should throw exception when updating field without id"() {
        given:
            def dto = new GenericFieldDTO()
            dto.name = "14"
            dto.fieldType = FieldType.CITY
            repository.save(_ as Field) >> { Field it -> it }
        when:
            service.updateField(dto)
        then:
            thrown(NotFoundException)
    }


    def "should generate random field"() {
        given:
            def dto1 = new Field()
            dto1.name = "14"

            def resultPageNumber = null
            repository.count() >> 3
            repository.findAll(_ as Pageable) >> { Pageable it ->
                resultPageNumber = it.getPageNumber()
                new PageImpl<>([dto1])
            }

        when:
            def response = service.getRandomField()

        then:
            response.name == dto1.name
            0 <= resultPageNumber
            resultPageNumber < 3
    }

    def "should throw exception when generating random field"() {
        given:
            repository.findAll(_ as Pageable) >> { Pageable it ->
                new PageImpl<>([])
            }
        when:
            service.getRandomField()

        then:
            thrown(RuntimeException)
    }
}
