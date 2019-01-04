package com.practicaFinal.articulos.servicios;

import java.util.List;
import java.util.Optional;

import com.practicaFinal.articulos.entidades.Art;
import com.practicaFinal.articulos.repositorios.ArtPagRepository;
import com.practicaFinal.articulos.repositorios.ArtRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArtServices {

    @Autowired
    ArtRepository articuloRepository;

    @Autowired
    private ArtPagRepository articuloPaginacionRepository;

    public void crearArticulo(Art articulo) {

        articuloRepository.save(articulo);
    }

    public List<Art> buscarTodos(){

        return articuloRepository.findAll();
    }

    public void borrarArticulo(Art articulo){

        articuloRepository.delete(articulo);
    }

    public Art buscarPorId(Long id) {

        Optional<Art> articulo = articuloRepository.findById(id);

        return articulo.orElse(null);

    }

    public List<Art> buscarArticulosPorNombre(String nombre) {
        return articuloRepository.findAllByNombreIgnoreCaseContaining(nombre);
    }

    public List<Art> paginacionDeArticulos(Pageable page) {

        return articuloPaginacionRepository.findAll(page).getContent();
    }

    public List<Art> buscarArticulosPorCantidadDisponibleMayorQue(int cantidad) {

        return articuloRepository.findAllByCantidadDisponibleGreaterThanEqual(cantidad);
    }

    public void restarCantidadArticulo(Art articulo, int cantidadMenos) {

        articulo.setCantidadDisponible(articulo.getCantidadDisponible() - cantidadMenos);
        articuloRepository.save(articulo);
    }

    public long contarArticulos() {

        return articuloRepository.count();
    }

}
