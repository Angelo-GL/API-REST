package com.company.desafio.desafioapi.service;

import com.company.desafio.desafioapi.dto.MessageResponseDTO;
import com.company.desafio.desafioapi.exception.NotFoundException;
import com.company.desafio.desafioapi.model.Pessoa;
import com.company.desafio.desafioapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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


    public List<Pessoa> listPessoa ()throws NotFoundException{
        List<Pessoa> result = repository.findAll();
        return result;
    }

    public Pessoa listPessoaId(Long id) throws NotFoundException{
        Optional<Pessoa> resultaPessoa = repository.findById(id);
        if(resultaPessoa.isPresent()){
            return resultaPessoa.get();
        }else {
            return null;
        }
    }


    private MessageResponseDTO createMessageResponse(Long id) {
        return new MessageResponseDTO(" Pessoa criada com ID " + id);
    }


    private MessageResponseDTO createMessaResponseUpdate(Long id, int cod){
        if(cod == 1){
            return new MessageResponseDTO("Pessoa de ID = " + id +" atualzada");
        }else {
            return new MessageResponseDTO("Erro ao atualizar pessoa de ID = " + id);
        }
    }

}
