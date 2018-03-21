package com.baciu.converter;

public interface SimpleConverter<S, T> {
	
	T toDTO(S source);
	S toEntity(T source);
	Iterable<T> toDTO(Iterable<S> source);	
	Iterable<S> toEntity(Iterable<T> source);

}
