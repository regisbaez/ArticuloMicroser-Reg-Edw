package com.practicaFinal.articulos.repositorios;

import com.practicaFinal.articulos.entidades.Art;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtPagRepository extends PagingAndSortingRepository<Art, Long> {

    Page<Art> findAll(Pageable pageable);

}
