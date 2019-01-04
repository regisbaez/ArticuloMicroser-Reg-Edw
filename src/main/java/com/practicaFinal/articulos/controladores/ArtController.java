package com.practicaFinal.articulos.controladores;

import com.practicaFinal.articulos.entidades.Art;
import com.practicaFinal.articulos.servicios.ArtServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtController {

    @Autowired
    private ArtServices articuloServices;

    @GetMapping("/articulos")
    public List<Art> articulos() {

        return articuloServices.buscarTodos();
    }

    @GetMapping("/articulos/nombre/{nombre}")
    public List<Art> articulosPorNombre(@PathVariable String nombre) {

        return articuloServices.buscarArticulosPorNombre(nombre);
    }

    @RequestMapping(value = "/articulos", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Art> crearArticulo(@RequestBody Art articulo) {

        articuloServices.crearArticulo(articulo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/articulos", method = RequestMethod.PUT)
    public ResponseEntity<Art> actualizarArticulo(@RequestBody Art articulo) {

        articuloServices.crearArticulo(articulo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/articulos", method = RequestMethod.DELETE, params = {"id"})
    public ResponseEntity<Art> borrarArticulo(@RequestParam("id") Long id) {
        Art articulo = articuloServices.buscarPorId(id);
        articuloServices.borrarArticulo(articulo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/articulos/comprar", method = RequestMethod.PUT, params = {"id", "cantidad"})
    public ResponseEntity<Art> comprarArticulos(@RequestParam("id") Long id, @RequestParam("cantidad") int cantidad) {

        Art articulo = articuloServices.buscarPorId(id);

        articuloServices.restarCantidadArticulo(articulo, cantidad);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/articulos/paginacion", method = RequestMethod.GET, params = {"limit", "offset"}, produces = {"application/json"})
    public List<Art> articulosPaginacion(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {

        Pageable pageable = PageRequest.of(offset, limit);
        return articuloServices.paginacionDeArticulos(pageable);
    }

    @GetMapping("/articulos/{id}")
    public Art buscarPorId(@PathVariable Long id) {

        return articuloServices.buscarPorId(id);
    }

    @GetMapping("/articulos/cantidad")
    public int contarArticulos(){

        return (int) articuloServices.contarArticulos();
    }


}
