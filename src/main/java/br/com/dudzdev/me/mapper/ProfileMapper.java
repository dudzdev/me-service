package br.com.dudzdev.me.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.dudzdev.me.dto.ProfileDTO;
import br.com.dudzdev.me.entity.Profile;

/**
 * Mapper for the entity {@link Profile} and its DTO {@link ProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {


    @Mapping(target = "badges", ignore = true)
    @Mapping(target = "removeBadge", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "removeContact", ignore = true)
    Profile toEntity(ProfileDTO profileDTO);

    default Profile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setId(id);
        return profile;
    }
}
