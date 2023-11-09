package br.com.rest.springboot.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

	private static final long serialVersionUID = 1L;

//	@JsonProperty("id")
//	@Mapping("id")
//	private Long key;
	private Long id;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	private String address;
	
	//@JsonIgnore (does not display in the request return)
	private String gender;
	
	private Boolean enabled;
	
	public PersonVO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
//	public Long getKey() {
//		return key;
//	}
//
//	public void setKey(Long key) {
//		this.key = key;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(address, enabled, firstName, gender, id, lastName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return Objects.equals(address, other.address) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName);
	}
	
}