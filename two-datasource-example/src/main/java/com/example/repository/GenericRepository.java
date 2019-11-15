package com.example.repository;

import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E,K> {
	Optional<E> findOne(K key);
	Collection<E> findAll();
	E add(E entity);
	E update(E entity);
	Optional<E> remove(K key);
}