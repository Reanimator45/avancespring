package com.example.transportadora.controladores;

import com.example.transportadora.entidades.Mercancia;
import com.example.transportadora.servicios.MercanciaServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mercancia")
@Tag(name="Servicio mercancias",description =  "Crud mercancias")
public class MercanciaControlador {
    @Autowired
    protected MercanciaServicio servicioMercancia;
    @PostMapping
    @Operation(summary ="Registrar mercancia en la BD")
    @ApiResponses(value={
            @ApiResponse(responseCode="201",description = "La mercania fue creada conrrectamente"),
            @ApiResponse(responseCode="400",description = "Error al crear la mercancia")
    })
    public ResponseEntity<Mercancia> registrar(@RequestBody Mercancia datosAGuardar){
        try{
            Mercancia mercanciaRegistrada = servicioMercancia.registrar(datosAGuardar);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(mercanciaRegistrada);
        }catch (Exception error){
            String mensaje="Error al registrar"+error.getMessage();
            Mercancia mercanciaConError = new Mercancia();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mercanciaConError);
        }
    }

    @GetMapping
    public ResponseEntity<List<Mercancia>>buscarTodos(){
        try{
            List<Mercancia> mercancia= servicioMercancia.buscarTodos();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mercancia);
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Mercancia>buscarPorId(@PathVariable Integer id ) {
        try {
            Mercancia mercanciaEncontrada = servicioMercancia.buscarPorId(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mercanciaEncontrada);

        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);

        }
    }

}
