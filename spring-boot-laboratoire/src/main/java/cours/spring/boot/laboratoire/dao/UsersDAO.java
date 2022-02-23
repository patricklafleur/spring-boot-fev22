package cours.spring.boot.laboratoire.dao;

import java.util.ArrayList;
import java.util.List;

import cours.spring.boot.laboratoire.models.User;
import org.springframework.stereotype.Component;


@Component
public class UsersDAO {

	// Dummy database. Initialize with some dummy values.
	private static List<User> users = new ArrayList<User>(){
		{
			add(new User(101, "John", "Doe", "djohn@gmail.com", "121-232-3435"));
			add(new User(201, "Roger", "Bontemps", "roger.bontemps@gmail.com", "343-545-2345"));
			add(new User(301, "Kate", "Williams", "kwilliams@gmail.com", "876-237-2987"));
			add(new User(System.currentTimeMillis(), "Bob", "Gratton", "bob@gratton.com", "356-758-8736"));
		}
	};

	/**
	 * Returns list of users from dummy database.
	 * 
	 * @return list of users
	 */
	public List<User> list() {
		return users;
	}

	/**
	 * Return user object for given id from dummy database. If user is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            user id
	 * @return user object for given id
	 */
	public User get(Long id) {

		for (User c : users) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Create new user in dummy database. Updates the id and insert new
	 * user in list.
	 * 
	 * @param user
	 *            user object
	 * @return user object with updated id
	 */
	public User create(User user) {
		user.setId(System.currentTimeMillis());
		users.add(user);
		return user;
	}

	/**
	 * Delete the user object from dummy database. If user not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the user id
	 * @return id of deleted user object
	 */
	public Long delete(Long id) {

		for (User c : users) {
			if (c.getId().equals(id)) {
				users.remove(c);
				return id;
			}
		}

		return null;
	}

	/**
	 * Update the user object for given id in dummy database. If user
	 * not exists, returns null
	 * 
	 * @param id
	 * @param user
	 * @return user object with id
	 */
	public User update(Long id, User user) {

		for (User c : users) {
			if (c.getId().equals(id)) {
				user.setId(c.getId());
				users.remove(c);
				users.add(user);
				return user;
			}
		}

		return null;
	}

}