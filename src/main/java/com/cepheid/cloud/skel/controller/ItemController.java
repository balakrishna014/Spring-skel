package com.cepheid.cloud.skel.controller;

import java.util.Collection;
import java.util.Optional;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cepheid.cloud.skel.exceptions.CustomException;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.ItemRepository;

import io.swagger.annotations.Api;


// curl http:/localhost:9443/app/api/1.0/items

@Component
@Path("/api/1.0/items")
@Api()
@RestController
public class ItemController {

  private final ItemRepository mItemRepository;

  @Autowired
  public ItemController(ItemRepository itemRepository) {
    mItemRepository = itemRepository;
  }

  @GetMapping
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public ResponseEntity<Collection<Item>> getItems() {
      Collection result = mItemRepository.findAll();
      return ResponseEntity.ok(result);
  }

  @GetMapping("/api/1.0/items/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public ResponseEntity<Item> getItemById(@PathVariable Long id) {
      Optional<Item> result = mItemRepository.findById(id);
      return ResponseEntity.ok(result.get());
  }
  
  @PostMapping
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(propagation = Propagation.REQUIRED)
  public ResponseEntity<Item> saveItem(@RequestBody Item item) throws CustomException {
      if (item != null && item.getId() != null) {
          throw new CustomException("Item cannot be created as Id is present");
      }
      Item result = mItemRepository.save(item);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @PutMapping
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(propagation = Propagation.REQUIRED)
  public ResponseEntity<Item> updateItem(@RequestBody Item item) throws CustomException {
      if (item != null && item.getId() == null) {
          throw new CustomException("Item cannot be updated as Id is not present");
      }

      Item result = mItemRepository.save(item);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }


  @DeleteMapping("/api/1.0/items/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(propagation = Propagation.REQUIRED)
  public void deleteItem(@PathVariable Long id) throws CustomException {
      mItemRepository.deleteById(id);
  }
}
