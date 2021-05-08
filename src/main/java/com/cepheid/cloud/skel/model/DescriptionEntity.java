package com.cepheid.cloud.skel.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DescriptionEntity extends AbstractEntity{

	@Column
	private String description;

	public DescriptionEntity() {
	
	}
	
	public DescriptionEntity(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		 return Objects.hash(super.hashCode(), description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        DescriptionEntity that = (DescriptionEntity) obj;
        return Objects.equals(description, that.description);
	}

	@Override
	public String toString() {
		return "DescriptionEntity [description=" + description + ", mId=" + mId + "]";
	}

	
	
	
}
