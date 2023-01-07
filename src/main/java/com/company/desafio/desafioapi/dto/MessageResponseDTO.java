package com.company.desafio.desafioapi.dto;

public class MessageResponseDTO {
    private String mensagem;

    public MessageResponseDTO() {
    }

    public MessageResponseDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
