package cours.spring.boot.laboratoire.models;

import java.util.Date;

import org.springframework.core.style.ToStringCreator;

import javax.validation.constraints.*;

public class User {
	private Long id;

	@NotNull
	@NotBlank
	@Size(min=2, max=64)
	private String firstName;

	@NotNull
	@NotBlank
	@Size(min=2)
	private String lastName;

	@NotNull
	@NotBlank()
	@Email
	private String email;

	@Pattern(regexp="^([0-9]{3}) [0-9]{3}-[0-9]{4}$")  // "1" "(012) 345-0679"
	private String mobile;

	@NotNull
	@PastOrPresent
	private Date dateOfBirth = null;

	public User() {
		this(-1, "", "", "", "");
	}

	public User(long id, String firstName, String lastName, String email, String mobile) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.dateOfBirth = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String toString(){
		return new ToStringCreator(this).append("id", id).append("email", email).toString();
	}

}