package br.com.dudzdev.me.resource;

import br.com.dudzdev.me.service.ContactService;
import io.swagger.annotations.ApiOperation;
import br.com.dudzdev.me.dto.ContactDTO;

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
 * REST controller for managing {@link br.com.dudz.me.domain.Contact}.
 */
@RestController
@RequestMapping("/v1/me")
public class ContactResource {

    private final Logger log = LoggerFactory.getLogger(ContactResource.class);

    private final ContactService contactService;

    public ContactResource(final ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contacts")
    @ApiOperation(value = "Create a new contact")
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody final ContactDTO contactDTO) throws URISyntaxException {
        log.debug("REST request to save Contact : {}", contactDTO);
        final ContactDTO result = contactService.save(contactDTO);
        return ResponseEntity.created(new URI("/api/contacts/" + result.getId())).body(result);
    }

    @PutMapping("/contacts")
    @ApiOperation(value = "Update contact")
    public ResponseEntity<ContactDTO> updateContact(@Valid @RequestBody final ContactDTO contactDTO) {
        log.debug("REST request to update Contact : {}", contactDTO);
        final ContactDTO result = contactService.save(contactDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/contacts")
    @ApiOperation(value = "Get all contacts")
    public ResponseEntity<List<ContactDTO>> getAllContacts(final Pageable pageable) {
        log.debug("REST request to get a page of Contacts");
        final Page<ContactDTO> page = contactService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/contacts/{id}")
    @ApiOperation(value = "Get contact by id")
    public ResponseEntity<ContactDTO> getContact(@PathVariable final Long id) {
        log.debug("REST request to get Contact : {}", id);
        final Optional<ContactDTO> contactDTO = contactService.findOne(id);
        return ResponseEntity.ok().body(contactDTO.orElse(new ContactDTO()));
    }

    @DeleteMapping("/contacts/{id}")
    @ApiOperation(value = "Delete contact by id")
    public ResponseEntity<Void> deleteContact(@PathVariable final Long id) {
        log.debug("REST request to delete Contact : {}", id);
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
