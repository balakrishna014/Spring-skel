package com.cepheid.cloud.skel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.cepheid.cloud.skel.model.DescriptionEntity;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.ItemState;
import com.cepheid.cloud.skel.repository.ItemRepository;

@RunWith(SpringRunner.class)
public class ItemControllerTest extends TestBase {

	private Long queryItemId = 3L;
	private String updateName="updateName";
    private Long deleteItemId = 4L;
    
    @Autowired
    private ItemRepository itemRepository;
  
  @Test
  public void testGetItemById() throws Exception {
      Builder itemController = getBuilder("/api/1.0/items/" + queryItemId);
      Item item = itemController.get(Item.class);
      assertThat(item != null);
      assertThat(item.getId().equals(queryItemId));
  }
  
  @Test
  public void testPostItems() throws Exception {
      Builder itemController = getBuilder("/api/1.0/items");
      Item item = itemController.post(getItemEntity(), Item.class);
      assertThat(item.getId() != null);
      assertThat(item.getName().equals(getItemEntity().getEntity().getClass().getName()));
  }

  
  private Entity getItemEntity() {
	  Item item = new Item();
      item.setName("Hobbit");
      item.setItemState(ItemState.VALID);
      item.setDescriptions(getDescription());

      return Entity.entity(item, MediaType.APPLICATION_JSON);
  }

  
  private Set<DescriptionEntity> getDescription() {
	   Set<DescriptionEntity> result = new HashSet<>();

	   DescriptionEntity description = new DescriptionEntity();
       description.setDescription("ItemDesc");

       result.add(description);
       return result;
  }
  
  @Test
  public void testPutItems() {
      Builder itemController = getBuilder("/api/1.0/items");
      Entity<Item> entityItem = getItemEntity();
      entityItem.getEntity().setId(queryItemId);
      entityItem.getEntity().setName(updateName);
      Item item = itemController.put(entityItem, Item.class);
      assertThat(item.getId() != null);
      assertThat(item.getName().equals(updateName));
  }
  
  @Test
  public void testGetItems() throws Exception {
    Collection<Item> items=itemRepository.findAll();
    assertThat(items.size() > 0);
  }
  
  @Test
  public void testDeleteItemById() {
      Builder itemController = getBuilder("/api/1.0/items/" + deleteItemId);
      itemController.delete();
      assertThat(itemRepository.findById(deleteItemId).isPresent() == false);
  }
}
