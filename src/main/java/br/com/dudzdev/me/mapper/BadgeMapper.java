package br.com.dudzdev.me.mapper;


import br.com.dudzdev.me.entity.*;
import br.com.dudzdev.me.dto.BadgeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Badge} and its DTO {@link BadgeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface BadgeMapper extends EntityMapper<BadgeDTO, Badge> {

    @Mapping(source = "profile.id", target = "profileId")
    BadgeDTO toDto(Badge badge);

    @Mapping(source = "profileId", target = "profile")
    Badge toEntity(BadgeDTO badgeDTO);

    default Badge fromId(Long id) {
        if (id == null) {
            return null;
        }
        Badge badge = new Badge();
        badge.setId(id);
        return badge;
    }
}
