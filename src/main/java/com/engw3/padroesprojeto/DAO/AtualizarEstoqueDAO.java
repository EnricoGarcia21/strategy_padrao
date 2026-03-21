package com.engw3.padroesprojeto.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.engw3.padroesprojeto.util.Conexao;

@Repository
public class AtualizarEstoqueDAO {
    public boolean adicionarEstoque(int produtoId, int quantidadeAAdicionar, Conexao conexao) {
        String sql = "UPDATE produto SET prod_quant = prod_quant + ? WHERE prod_id = ?";

        try (PreparedStatement pst = conexao.getConnect().prepareStatement(sql)) {
            pst.setInt(1, quantidadeAAdicionar);
            pst.setInt(2, produtoId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean subtrairEstoque(int produtoId, int quantidadeASubtrair, Conexao conexao) {
        String sql = "UPDATE produto SET prod_quant = prod_quant - ? " +
                "WHERE prod_id = ? AND prod_quant >= ?";

        try (PreparedStatement pst = conexao.getConnect().prepareStatement(sql)) {
            pst.setInt(1, quantidadeASubtrair);
            pst.setInt(2, produtoId);
            pst.setInt(3, quantidadeASubtrair);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
