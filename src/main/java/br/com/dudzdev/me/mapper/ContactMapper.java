package br.com.dudzdev.me.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.dudzdev.me.dto.ContactDTO;
import br.com.dudzdev.me.entity.Contact;

/**
 * Mapper for the entity {@link Contact} and its DTO {@link ContactDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface ContactMapper extends EntityMapper<ContactDTO, Contact> {

    @Mapping(source = "profile.id", target = "profileId")
    ContactDTO toDto(Contact contact);

    @Mapping(source = "profileId", target = "profile")
    Contact toEntity(ContactDTO contactDTO);

    default Contact fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
}
