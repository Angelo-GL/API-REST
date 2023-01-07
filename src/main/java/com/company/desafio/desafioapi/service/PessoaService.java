package com.company.desafio.desafioapi.service;

import com.company.desafio.desafioapi.dto.MessageResponseDTO;
import com.company.desafio.desafioapi.exception.NotFoundException;
import com.company.desafio.desafioapi.model.Pessoa;
import com.company.desafio.desafioapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;
    public MessageResponseDTO createPessoa(Pessoa pessoa) {
        Pessoa salvarPessoa = repository.save(pessoa);
        return createMessageResponse(salvarPessoa.getId());
    }
    public MessageResponseDTO updatePessoa(Long id, Pessoa pessoa){
        Optional<Pessoa> buscaPessoa = repository.findById(id);
        if(buscaPessoa.isPresent()){
            Pessoa _pessoa = buscaPessoa.get();
            _pessoa.setNome(pessoa.getNome());
            _pessoa.setNascimento(pessoa.getNascimento());
            _pessoa.setEnderecos(pessoa.getEnderecos());
            repository.save(_pessoa);
            return createMessaResponseUpdate(_pessoa.getId(), 1);
        }else {
            return createMessaResponseUpdate(id, 0);
        }
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
    private MessageResponseDTO createMessaResponseUpdate(Long id, int cod){
        if(cod == 1){
            return new MessageResponseDTO("Pessoa de ID " + id +" atualzada");
        }else {
            return new MessageResponseDTO("Erro ao atualizar pessoa de ID " + id);
        }

    }

}
