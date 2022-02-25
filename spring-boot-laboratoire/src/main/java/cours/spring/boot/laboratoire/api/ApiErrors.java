package cours.spring.boot.laboratoire.api;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiErrors {
	// "email" -> ["le format", "le champs est requis"]
	Map<String, List<String>> errors = new HashMap<>();
	
	public ApiErrors() {}

	public ApiErrors(BindingResult bindingResult) {
		for(FieldError fe : bindingResult.getFieldErrors()) {
			add(fe.getField(), fe.getDefaultMessage());
		}
	}

	public ApiErrors add(String property, String message) {
		getPropertyErrors(property).add(message);
		return this;
	}

	public ApiErrors add(String property, List<String> messages) {
		List<String> propsErrors = getPropertyErrors(property);
		propsErrors.addAll(messages);
		return this;
	}

	private List<String> getPropertyErrors(String property) {
		List<String> propsErrors = errors.get(property);

		if (propsErrors == null) {
			propsErrors = new ArrayList<String>();
			errors.put(property, propsErrors);
		}

		return propsErrors;
	}
	
	
	@Override
	public String toString() {
		return "ApiErrors [errors=" + errors + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiErrors other = (ApiErrors) obj;
		if (errors == null) {
			if (other.errors != null)
				return false;
		} else if (!errors.equals(other.errors))
			return false;
		return true;
	}

	public Map<String, List<String>> getErrors() {
		return errors;
	}

}
