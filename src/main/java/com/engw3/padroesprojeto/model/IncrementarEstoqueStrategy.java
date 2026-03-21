package com.engw3.padroesprojeto.model;

import com.engw3.padroesprojeto.DAO.AtualizarEstoqueDAO;
import com.engw3.padroesprojeto.util.Conexao;

public class IncrementarEstoqueStrategy implements MovimentoEstoqueStrategy {
    private static final AtualizarEstoqueDAO dao = new AtualizarEstoqueDAO();

    @Override
    public boolean executar(Produto produto, int quantidade, Conexao conexao) {
        return dao.adicionarEstoque(produto.getProdId(), quantidade, conexao);
    }
}
