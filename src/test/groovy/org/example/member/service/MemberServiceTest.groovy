package org.example.member.service

import org.example.member.dto.MemberDto
import org.example.member.entity.Member
import org.example.member.repository.MemberRepository
import spock.lang.Specification
import java.sql.Date;

class MemberServiceTest extends Specification {
    MemberRepository repository = Mock()
    MemberService service = new MemberService(repository)

    def "회원가입 테스트"() {
        given:
        Date birthDate = Date.valueOf("1990-01-01")
        MemberDto dto = new MemberDto(memberId: "id1", name: "John Doe", birth: birthDate)

        when:
        repository.addMember(_) >> dto
        Member result = service.registerMember(dto)

        then:
        result.memberId == dto.memberId
        result.name == dto.name
        result.birth == dto.birth
        1 * repository.addMember(dto)
    }

    def "회원조회 테스트"() {
        given:
        String memberId = "id2"
        Date birthDate = Date.valueOf("1992-02-02")
        MemberDto expectedDto = new MemberDto(memberId: memberId, name: "Jane Doe", birth: birthDate)
        Member expectedMember = Member.builder().memberId(expectedDto.getMemberId())
                .name(expectedDto.getName())
                .birth(expectedDto.getBirth())
                .build()


        when:
        repository.findMemberById(memberId) >> expectedDto
        Member result = service.findMemberById(memberId)

        then:
        result.memberId == expectedMember.memberId
        result.name == expectedMember.name
        result.birth == expectedMember.birth
    }

    def "회원탈퇴 테스트"() {
        given:
        String memberId = "id3"

        when:
        service.deleteMember(memberId)

        then:
        1 * repository.deleteMember(memberId)
    }
}
