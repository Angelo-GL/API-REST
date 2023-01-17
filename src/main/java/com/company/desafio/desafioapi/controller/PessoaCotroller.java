package com.company.desafio.desafioapi.controller;

import com.company.desafio.desafioapi.dto.MessageResponseDTO;
import com.company.desafio.desafioapi.exception.NotFoundException;
import com.company.desafio.desafioapi.model.Endereco;
import com.company.desafio.desafioapi.model.Pessoa;
import com.company.desafio.desafioapi.service.EnderecoService;
import com.company.desafio.desafioapi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaCotroller {
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Pessoa> listarPessoa (){
        return pessoaService.listPessoa();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO criarPessoa (@RequestBody @Validated Pessoa pessoa){
        return pessoaService.createPessoa(pessoa);
    }


    @PostMapping(value = "/{id}/endereco")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO criarEndereco(@RequestBody @Validated Endereco endereco, @PathVariable Long id) throws NotFoundException {
        Pessoa pessoa = pessoaService.listPessoaId(id);
        if(pessoa != null){
            return enderecoService.createEndereco(pessoa, endereco);
        }else {
            return new MessageResponseDTO("Endereço não encontrado");
        }

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) throws NotFoundException {
        Pessoa pessoa = pessoaService.listPessoaId(id);
        return ResponseEntity.ok().body(pessoa);
    }


    @PutMapping(value = "/{id}")
    public  MessageResponseDTO atualizarPessoa(@PathVariable Long id, @RequestBody @Validated Pessoa pessoa){
        return pessoaService.updatePessoa(id, pessoa);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id){
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
