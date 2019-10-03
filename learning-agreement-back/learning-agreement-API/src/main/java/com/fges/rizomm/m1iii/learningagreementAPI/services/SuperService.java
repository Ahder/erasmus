package com.fges.rizomm.m1iii.learningagreementAPI.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class SuperService<Entity, Dto> {

    private final ModelMapper mapper;

    public abstract Dto entityToDto(Entity e);

    public abstract Entity dtoToEntity(Dto dto);

    public SuperService() {
        this.mapper = new ModelMapper();
    }

    public List<Dto> entityListToDtoList(List<Entity> entityList) {
        List<Dto> dtoList = new ArrayList<>();
        for (Entity tmp: entityList) {
            dtoList.add(this.entityToDto(tmp));
        }
        return dtoList;
    }

    public List<Entity> dtoListToEntityList(List<Dto> dtoList) {
        List<Entity> entityList = new ArrayList<>();
        for (Dto tmp: dtoList) {
            entityList.add(this.dtoToEntity(tmp));
        }
        return entityList;
    }

}
