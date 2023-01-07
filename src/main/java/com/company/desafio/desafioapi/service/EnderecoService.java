package com.company.desafio.desafioapi.service;

import com.company.desafio.desafioapi.dto.MessageResponseDTO;
import com.company.desafio.desafioapi.model.Endereco;
import com.company.desafio.desafioapi.model.Pessoa;
import com.company.desafio.desafioapi.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public MessageResponseDTO createEndereco (Pessoa pessoa, Endereco endereco){
        endereco.setPessoa(pessoa);
        Endereco salvarEndereco = repository.save(endereco);
        return createMessageResponse(salvarEndereco.getId());
    }

    private MessageResponseDTO createMessageResponse(Long id) {
        return new MessageResponseDTO("Created Endereco with ID " + id);
    }

}
