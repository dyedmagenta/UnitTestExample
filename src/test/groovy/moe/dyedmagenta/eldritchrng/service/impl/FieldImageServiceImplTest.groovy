package moe.dyedmagenta.eldritchrng.service.impl

import moe.dyedmagenta.eldritchrng.dto.FieldImageDTO
import moe.dyedmagenta.eldritchrng.dto.FieldType
import moe.dyedmagenta.eldritchrng.dto.GenericFieldDTO
import moe.dyedmagenta.eldritchrng.error.NotFoundException
import moe.dyedmagenta.eldritchrng.error.ValidationException
import moe.dyedmagenta.eldritchrng.model.Field
import moe.dyedmagenta.eldritchrng.model.FieldImage
import moe.dyedmagenta.eldritchrng.repository.FieldImageRepository
import moe.dyedmagenta.eldritchrng.repository.FieldRepository
import moe.dyedmagenta.eldritchrng.service.FieldImageService
import moe.dyedmagenta.eldritchrng.service.FieldService
import moe.dyedmagenta.eldritchrng.validation.FieldValidator
import spock.lang.Specification

class FieldImageServiceImplTest extends Specification {

    def repository = Stub(FieldImageRepository)

    FieldImageService service = new FieldImageServiceImpl(repository)

    def "should update image"() {
        given:
            def dto = new FieldImageDTO()
            dto.name = "Image1"
            dto.id = UUID.randomUUID()

        and: "stub findById entity with expected id"
            def mockEntity = Mock(FieldImage)
            repository.findById(dto.id) >> { UUID it -> Optional.of(mockEntity) }

        when:
            service.updateImage(dto)

        then:
            1 * mockEntity.setName(dto.name)
    }

    def "should throw exception when updating field without name"() {
        given:
            def dto = new FieldImageDTO()
            dto.name = ""
            repository.save(_ as FieldImage) >> { FieldImage it -> it }
        when:
            service.updateImage(dto)
        then:
            thrown(ValidationException)
    }

    def "should throw exception when updating field without id"() {
        given:
            def dto = new FieldImageDTO()
            dto.name = "123"
            repository.findById(_ as UUID) >> { UUID it -> Optional.empty() }
        when:
            service.updateImage(dto)
        then:
            thrown(NotFoundException)
    }
}
