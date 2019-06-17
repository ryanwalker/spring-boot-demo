package com.kubra.rest;

import com.kubra.rest.models.Contact;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "/contacts",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ContactsController {


  private MessageSource messageSource;

  public ContactsController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }


  public static final Map<UUID, Contact> contacts = new HashMap<>();

  @PostMapping
  public ResponseEntity<Contact> create(@RequestBody Contact contact) {
    UUID id = UUID.randomUUID();
    contact.setId(id);

    contacts.put(id, contact);
    URI uri = URI.create("contacts/" + id);
    return ResponseEntity.ok().location(uri).build();
  }

  @GetMapping
  public ResponseEntity<Collection<Contact>> list() {
    Collection<Contact> contactList = contacts.values();

    return ResponseEntity.ok(contactList);
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<Contact> get(@PathVariable UUID id) {
    Contact contact = contacts.get(id);

    if (Objects.isNull(contact)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(contact);
    }
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity delete(@PathVariable UUID id) {
    if (!contacts.containsKey(id)) {
      return ResponseEntity.notFound().build();
    } else {
      contacts.remove(id);
      return ResponseEntity.noContent().build();
    }

  }

  @PutMapping(path = "{id}")
  public ResponseEntity<Contact> update(@PathVariable UUID id, @RequestBody Contact contact) {

    if (Objects.isNull(contacts.get(id))) {
      return ResponseEntity.notFound().build();
    } else {
      contact.setId(id);
      contacts.put(id, contact);
      return ResponseEntity.ok(contact);
    }

  }

  @GetMapping("secret")
  public ResponseEntity<Contact> secretContact(@RequestHeader(required = false, name = "X-Secret-Code") String xSecretCode) {
    if (!"42".equals(xSecretCode)) {
      return ResponseEntity.notFound().build();
    }
    Contact secretContact = new Contact();
    secretContact.setName("James Bond");

    return ResponseEntity.ok(secretContact);
  }



}
