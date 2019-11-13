package com.example.world.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public interface GenericRepository<E, K> {
	Optional<E> findById(K k);

	List<E> findAll(int pageNo, int pageSize);

	// Flyweight Pattern
	Stream<E> findAllStream(int pageNo, int pageSize);

	void create(E e);

	void update(E e);

	Optional<E> removeById(K k);

	void remove(E e);

}
