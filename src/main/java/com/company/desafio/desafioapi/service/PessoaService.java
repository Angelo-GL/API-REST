package com.company.desafio.desafioapi.service;

import com.company.desafio.desafioapi.dto.MessageResponseDTO;
import com.company.desafio.desafioapi.exception.NotFoundException;
import com.company.desafio.desafioapi.model.Pessoa;
import com.company.desafio.desafioapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;
    public MessageResponseDTO createPessoa(Pessoa pessoa) {
        Pessoa salvarPessoa = repository.save(pessoa);
        return createMessageResponse(salvarPessoa.getId());
    }

    public Pessoa findById(Long id) throws NotFoundException{
            return verifyIfExists(id);
    }

    public List<Pessoa> findAll() {
        List<Pessoa> result = repository.findAll();
        return result.stream().map(Pessoa::new).collect(Collectors.toList());
    }

    private Pessoa verifyIfExists(Long id){
        return repository.findById(id).orElseThrow();
    }
    private MessageResponseDTO createMessageResponse(Long id) {
        return new MessageResponseDTO(" Pessoa criada com ID " + id);
    }

}
