package fr.nmocs.library.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.UserManagement;
import fr.nmocs.library.model.User;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;
import fr.nmocs.library.webservice.UserService;
import fr.nmocs.library.webservice.error.WebserviceException;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserManagement userMgmt;

	@Override
	public User createUser(User user) throws WebserviceException {
		try {
			return userMgmt.createUser(user);
		} catch (LibraryException le) {
			throw new WebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public User updateUser(User user) throws WebserviceException {
		try {
			return userMgmt.updateUser(user);
		} catch (LibraryException le) {
			throw new WebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public User findById(Integer id) {
		try {
			return userMgmt.findById(id);
		} catch (LibraryTechnicalException le) {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		try {
			return userMgmt.findByEmail(email);
		} catch (LibraryTechnicalException le) {
			return null;
		}
	}

	@Override
	public List<User> findByName(String name) {
		try {
			return userMgmt.findByName(name);
		} catch (LibraryTechnicalException le) {
			return new ArrayList<>();
		}
	}

	// ===== GESTION DES EXCEPTION

	private String getExceptionReason(LibraryException le) {
		String reason = le.getErrorCode().getId() + " => Error creating user : ";
		switch (le.getErrorCode()) {
		// FIELD MISSING
		case USER_UNSETTED:
			reason += "no user given";
			break;
		case USER_UNSETTED_LASTNAME:
			reason += "lastname not setted";
			break;
		case USER_UNSETTED_FIRSTNAME:
			reason += "firstname not setted";
			break;
		case USER_UNSETTED_EMAIL:
			reason += "email not setted";
			break;
		case USER_UNSETTED_PASSWORD:
			reason += "password not setted";
			break;
		// SIZE_OVERFLOW
		case USER_OVERSIZED_LASTNAME:
			reason += "lastname is too long";
			break;
		case USER_OVERSIZED_FIRSTNAME:
			reason += "firstname is too long";
			break;
		case USER_OVERSIZED_EMAIL:
			reason += "email is too long";
			break;
		case USER_OVERSIZED_PASSWORD:
			reason += "password is too long";
			break;
		// TECHNICAL
		case USER_DUPLICATED_EMAIL:
			reason += "email is already used";
			break;

		// NOT FOUND
		case USER_NOT_FOUND:
			reason += "no user found";
			break;

		default:
			reason += "unknown cause";
			break;
		}
		return reason;
	}

}
