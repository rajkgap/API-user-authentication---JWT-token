package com.SDEadda247.app.client.Userentity;

import org.springframework.data.repository.CrudRepository;

public interface userrepositry extends CrudRepository<userentity, Long> {
	userentity findByemail(String email);

}
