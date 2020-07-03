package br.com.dudzdev.me.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dudzdev.me.dto.BadgeDTO;
import br.com.dudzdev.me.service.BadgeService;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing {@link br.com.dudz.me.domain.Badge}.
 */
@RestController
@RequestMapping("/v1/me")
public class BadgeResource {

    private final Logger log = LoggerFactory.getLogger(BadgeResource.class);

    private final BadgeService badgeService;

    public BadgeResource(final BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @PostMapping("/badges")
    @ApiOperation(value = "Create a new badge")
    public ResponseEntity<BadgeDTO> createBadge(@Valid @RequestBody final BadgeDTO badgeDTO) throws URISyntaxException {
        log.debug("REST request to save Badge : {}", badgeDTO);
        final BadgeDTO result = badgeService.save(badgeDTO);
        return ResponseEntity.created(new URI("/api/badges/" + result.getId())).body(result);
    }

    @PutMapping("/badges")
    @ApiOperation(value = "Update badge")
    public ResponseEntity<BadgeDTO> updateBadge(@Valid @RequestBody final BadgeDTO badgeDTO) {
        log.debug("REST request to update Badge : {}", badgeDTO);
        final BadgeDTO result = badgeService.save(badgeDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/badges")
    @ApiOperation(value = "Get all badges")
    public ResponseEntity<List<BadgeDTO>> getAllBadges(final Pageable pageable) {
        log.debug("REST request to get a page of Badges");
        final Page<BadgeDTO> page = badgeService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/badges/{id}")
    @ApiOperation(value = "Get badge by id")
    public ResponseEntity<BadgeDTO> getBadge(@PathVariable final Long id) {
        log.debug("REST request to get Badge : {}", id);
        Optional<BadgeDTO> badgeDTO = badgeService.findOne(id);
        return ResponseEntity.ok().body(badgeDTO.orElse(new BadgeDTO()));
    }

    @DeleteMapping("/badges/{id}")
    @ApiOperation(value = "Delete badge by id")
    public ResponseEntity<Void> deleteBadge(@PathVariable final  Long id) {
        log.debug("REST request to delete Badge : {}", id);
        badgeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
