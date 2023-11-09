package at.fhtw.kaipel.todo.mapper;

import java.util.List;

/**
 * Provides contract for mapstruct code generation. <br/>
 * Custom mapper should implement this interface as well.
 *
 * @param <D> DTO
 * @param <E> Entity
 */
public interface EntityMapper<D, E> {

    E toEntity( D dto );

    D toDTO( E entity );

    List<D> toDTOList( List<E> entities );

    List<E> toEntityList( List<D> dtoList );
}