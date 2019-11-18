package com.example.world.dao;

import java.util.Collection;
import java.util.Optional;

public interface GenericDao<E, PK> {
	Optional<E> findOne(PK key);

	Collection<E> findAll(int pageNo, int pageSize);

	E add(E entity);

	E update(E entity);

	Optional<E> remove(PK key);
}
