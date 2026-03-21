package com.engw3.padroesprojeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.engw3.padroesprojeto.util.Conexao;
import com.engw3.padroesprojeto.util.SingletonDB;

@SpringBootApplication
public class PadroesprojetoApplication {

	public static void main(String[] args) {
		Conexao conexao = SingletonDB.conectar();
		
		if (conexao == null || !conexao.getEstadoConexao()) {
            System.out.println("❌ Não foi possível conectar ao banco de dados!");
        } else {
            System.out.println("✅ Conectado ao banco de dados com sucesso!");
        }
		SpringApplication.run(PadroesprojetoApplication.class, args);
	}

}
