package com.company.desafio.desafioapi.service;

import com.company.desafio.desafioapi.dto.MessageResponseDTO;
import com.company.desafio.desafioapi.exception.NotFoundException;
import com.company.desafio.desafioapi.model.Pessoa;
import com.company.desafio.desafioapi.repository.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PessoaServiceTest {
    public static final Long ID = 1L;
    @InjectMocks
    private PessoaService pessoaService;
    @Mock
    private PessoaRepository repository;

    private MessageResponseDTO mensagem;
    private Pessoa pessoa;
    private List<Pessoa> listPessoa;
    private Optional<Pessoa> optionalPessoa;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPessoa();
    }

    @Test
    void createPessoa() {
    }

    @Test
    void updatePessoa() {
    }

    @Test
    void listPessoa() {
    }

    @Test
    void quandoBuscarPorIdRetornarInstanciaDePessoa() throws NotFoundException {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalPessoa);

        Pessoa response = pessoaService.listPessoaId(ID);
        Assertions.assertEquals(Pessoa.class, response.getClass());
    }
    private void startPessoa(){
        pessoa = new Pessoa(ID, "Angelo", LocalDate.of(2022, 12,05),  null);
        mensagem = new MessageResponseDTO("Menssagem de resposta");
        listPessoa = List.of(pessoa);
        optionalPessoa = Optional.of(pessoa);

    }
}