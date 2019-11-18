package com.example.imdb.repository;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface CrudRepository<E, K> {
	Optional<E> findOneById(K id);

	List<E> findAll(int pageNo, int pageSize);

	Optional<E> save(E e);

	Optional<E> update(E e);

	Optional<E> removeById(K id);

	Optional<E> remove(E e);
}
