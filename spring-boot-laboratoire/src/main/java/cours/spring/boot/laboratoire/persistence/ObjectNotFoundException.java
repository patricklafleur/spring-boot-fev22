package cours.spring.boot.laboratoire.persistence;

public class ObjectNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Class<?> entityType;

	public ObjectNotFoundException(long id, Class<?> entityType) {
		this.id = id;
		this.entityType = entityType;
	}
	
	@Override
	public String getMessage() {
		return "Cannot find entity of type [" + this.entityType.getSimpleName() + "] with id: [" + this.id + "].";
	}
	
	public long getId() {
		return id;
	}
	public Class<?> getEntityType() {
		return entityType;
	}
	

}
