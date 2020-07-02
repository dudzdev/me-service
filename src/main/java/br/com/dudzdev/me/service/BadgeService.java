package br.com.dudzdev.me.service;

import br.com.dudzdev.me.entity.Badge;
import br.com.dudzdev.me.repository.BadgeRepository;
import br.com.dudzdev.me.dto.BadgeDTO;
import br.com.dudzdev.me.mapper.BadgeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Badge}.
 */
@Service
@Transactional
public class BadgeService {

    private final Logger log = LoggerFactory.getLogger(BadgeService.class);

    private final BadgeRepository badgeRepository;
    private final BadgeMapper badgeMapper;

    public BadgeService(final BadgeRepository badgeRepository, 
                        final BadgeMapper badgeMapper) {

        this.badgeRepository = badgeRepository;
        this.badgeMapper = badgeMapper;
    }

    public BadgeDTO save(final BadgeDTO badgeDTO) {
        log.debug("Service to save Badge : {}", badgeDTO);
        Badge badge = badgeMapper.toEntity(badgeDTO);
        badge = badgeRepository.save(badge);
        return badgeMapper.toDto(badge);
    }

    @Transactional(readOnly = true)
    public Page<BadgeDTO> findAll(final Pageable pageable) {
        log.debug("Service to get all Badges");
        return badgeRepository.findAll(pageable).map(badgeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<BadgeDTO> findOne(final Long id) {
        log.debug("Service to get Badge : {}", id);
        return badgeRepository.findById(id).map(badgeMapper::toDto);
    }

    public void delete(final Long id) {
        log.debug("Service to delete Badge : {}", id);
        badgeRepository.deleteById(id);
    }
}
