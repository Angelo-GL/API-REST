package com.company.desafio.desafioapi.controller;

import com.company.desafio.desafioapi.dto.MessageResponseDTO;
import com.company.desafio.desafioapi.exception.NotFoundException;
import com.company.desafio.desafioapi.model.Endereco;
import com.company.desafio.desafioapi.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listaEndereco() throws NotFoundException {
        return enderecoService.listEndereco();
    }


    @PutMapping("/{id}")
    public MessageResponseDTO atualizarEndPrincipal(@PathVariable Long id){
        return enderecoService.updateEndPrincipal(id);
    }
}
