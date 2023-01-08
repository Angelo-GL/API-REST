package com.company.desafio.desafioapi.service;

import com.company.desafio.desafioapi.dto.MessageResponseDTO;
import com.company.desafio.desafioapi.exception.NotFoundException;
import com.company.desafio.desafioapi.model.Endereco;
import com.company.desafio.desafioapi.model.Pessoa;
import com.company.desafio.desafioapi.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public MessageResponseDTO createEndereco (Pessoa pessoa, Endereco endereco){
        endereco.setPessoa(pessoa);
        Endereco salvarEndereco = repository.save(endereco);
        return createMessageResponse(salvarEndereco.getId());
    }


    public List<Endereco> listEndereco () throws NotFoundException{
        List <Endereco> enderecos = repository.findAll();
        return enderecos;
    }


    private MessageResponseDTO createMessageResponse(Long id) {
        return new MessageResponseDTO("Endereco criado com ID " + id);
    }

}
