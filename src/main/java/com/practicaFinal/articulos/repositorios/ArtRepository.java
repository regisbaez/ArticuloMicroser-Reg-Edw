package com.practicaFinal.articulos.repositorios;

import com.practicaFinal.articulos.entidades.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {


    List<Art> findAllByNombreIgnoreCaseContaining(String nombre);
    List<Art> findAllByCantidadDisponibleGreaterThanEqual(int cantidad);

    @Query(value = "select * from Articulo a offset(:offset) limit(:limit)", nativeQuery = true)
    List<Art> buscarArticulosPorPaginacion(@Param("offset") int offset, @Param("limit") int limit);





}
