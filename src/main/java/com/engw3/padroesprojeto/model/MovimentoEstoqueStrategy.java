package com.engw3.padroesprojeto.model;

import com.engw3.padroesprojeto.util.Conexao;

public interface MovimentoEstoqueStrategy {
    boolean executar(Produto produto, int quantidade, Conexao conexao);
}
