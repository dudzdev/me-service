package br.com.dudzdev.me.resource;

import br.com.dudzdev.me.service.ProfileService;
import io.swagger.annotations.ApiOperation;
import br.com.dudzdev.me.dto.ProfileDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.com.dudz.me.domain.Profile}.
 */
@RestController
@RequestMapping("/v1/me")
public class ProfileResource {

    private final Logger log = LoggerFactory.getLogger(ProfileResource.class);

    private final ProfileService profileService;

    public ProfileResource(final ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profiles")
    @ApiOperation(value = "Create a new profile")
    public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody final ProfileDTO profileDTO) throws URISyntaxException {
        log.debug("REST request to save Profile : {}", profileDTO);
        final ProfileDTO result = profileService.save(profileDTO);
        return ResponseEntity.created(new URI("/api/profiles/" + result.getId())).body(result);
    }

    @PutMapping("/profiles")
    @ApiOperation(value = "Update profile")
    public ResponseEntity<ProfileDTO> updateProfile(@Valid @RequestBody final ProfileDTO profileDTO) {
        log.debug("REST request to update Profile : {}", profileDTO);
        final ProfileDTO result = profileService.save(profileDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/profiles")
    @ApiOperation(value = "Get all profiles")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles(final Pageable pageable) {
        log.debug("REST request to get a page of Profiles");
        Page<ProfileDTO> page = profileService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/profiles/{id}")
    @ApiOperation(value = "Get profile by id")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable final Long id) {
        log.debug("REST request to get Profile : {}", id);
        Optional<ProfileDTO> profileDTO = profileService.findOne(id);
        return ResponseEntity.ok().body(profileDTO.orElse(new ProfileDTO()));
    }

    @DeleteMapping("/profiles/{id}")
    @ApiOperation(value = "Delete profile by id")
    public ResponseEntity<Void> deleteProfile(@PathVariable final Long id) {
        log.debug("REST request to delete Profile : {}", id);
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
