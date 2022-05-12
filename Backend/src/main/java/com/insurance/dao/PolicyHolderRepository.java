
package com.insurance.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.insurance.entity.PolicyHolder;
import com.insurance.entity.User;




// TODO: Auto-generated Javadoc
/**
 * The Interface PolicyHolderRepository.
 */
@Repository
public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Integer> {
	
	/**
	 * Find by id.
	 *
	 * @param policyholderId the policyholder id
	 * @return the optional
	 */
	public Optional<PolicyHolder> findById(int policyholderId);
	
	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<PolicyHolder> findByUser(User user);
	
	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<PolicyHolder> findByUser(User user,Pageable pageable);
	
}
