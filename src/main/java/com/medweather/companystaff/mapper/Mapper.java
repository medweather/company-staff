package com.medweather.companystaff.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class Mapper<E, A> {

    @Autowired
    ModelMapper mapper;

    private Class<E> entity;
    private Class<A> api;

    Mapper(Class<E> entity, Class<A> api) {
        this.entity = entity;
        this.api = api;
    }

    public E toEntity(A api) {
        return Objects.isNull(api)
                ? null
                : mapper.map(api, entity);
    }

    public A toApi(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, api);
    }

    Converter<E, A> toApiConverter() {
        return context -> {
            E source = context.getSource();
            A destination = context.getDestination();
            mapSpecificFieldsEA(source, destination);
            return context.getDestination();
        };
    }

    Converter<A, E> toEntityConverter() {
        return context -> {
            A source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFieldsAE(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFieldsEA(E source, A destination) {
    }

    void mapSpecificFieldsAE(A source, E destination) {
    }
}
