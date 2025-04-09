package me.anisjamadar.data.mapper

interface Mapper<Entity, DomainModel> {
    fun transformToEntity(domainModel: DomainModel): Entity
    fun transformToDomainModel(entity: Entity): DomainModel
}