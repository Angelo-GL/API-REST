package com.company.desafio.desafioapi.service;

import com.company.desafio.desafioapi.dto.MessageResponseDTO;
import com.company.desafio.desafioapi.exception.NotFoundException;
import com.company.desafio.desafioapi.model.Endereco;
import com.company.desafio.desafioapi.model.Pessoa;
import com.company.desafio.desafioapi.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public MessageResponseDTO updateEndPrincipal(Long id){
        Optional<Endereco> endereco = repository.findById(id);
        if(endereco.isPresent()){
            Endereco _endereco = endereco.get();
            _endereco.setPrincial(true);
            repository.save(_endereco);
            return createMessageUpdateEndPrincipal(id, 1);
        }else{
            return createMessageUpdateEndPrincipal(id, 0);
        }

    }


    private MessageResponseDTO createMessageResponse(Long id) {
        return new MessageResponseDTO("Endereco criado com ID = " + id);
    }

    private MessageResponseDTO createMessageUpdateEndPrincipal(Long id, Integer num){
        if(num == 1){
            return new MessageResponseDTO("Endereço Principal de id = " + id +  " atualizado" );
        }else {
            return new MessageResponseDTO("Endereco não encontrado");
        }
    }
}
