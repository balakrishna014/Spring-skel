package com.cepheid.cloud.skel.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Item extends AbstractEntity {

	@Column
	private String name;
	
	@Enumerated(EnumType.STRING)
	private ItemState itemState;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="desc_id",referencedColumnName="mid")
	private Set<DescriptionEntity> descriptions;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(String name, ItemState itemState, Set<DescriptionEntity> descriptions) {
		super();
		this.name = name;
		this.itemState = itemState;
		this.descriptions = descriptions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemState getItemState() {
		return itemState;
	}

	public void setItemState(ItemState itemState) {
		this.itemState = itemState;
	}

	public Set<DescriptionEntity> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<DescriptionEntity> descriptions) {
		this.descriptions = descriptions;
	}

	@Override
	public int hashCode() {
		  return Objects.hash(super.hashCode(), name, itemState, descriptions);
	}

	@Override
	public boolean equals(Object obj) {
		 if (this ==obj) return true;
	        if (obj == null || getClass() !=obj.getClass()) return false;
	        if (!super.equals(obj)) return false;
	        Item item = (Item) obj;
	        return Objects.equals(name, item.name) && itemState == item.itemState && Objects.equals(descriptions, item.descriptions);
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", itemState=" + itemState + ", descriptions=" + descriptions + ", mId=" + mId
				+ "]";
	}
	
}
