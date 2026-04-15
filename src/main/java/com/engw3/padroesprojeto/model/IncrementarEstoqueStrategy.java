import com.engw3.padroesprojeto.DAO.AtualizarEstoqueDAO;
import com.engw3.padroesprojeto.util.Conexao;

public class IncrementarEstoqueStrategy implements MovimentoEstoqueStrategy {
    private static final AtualizarEstoqueDAO dao = new AtualizarEstoqueDAO();

    @Override
    public boolean executar(Produto produto, int quantidade, Conexao conexao) {
        if(produto.getProdId() <= 0 || quantidade == 0) {
            return false;
        }else if(quantidade < 0) {
            this.movimentoEstoqueStrategy = new DecrementarEstoqueStrategy();
            return movimentoEstoqueStrategy.executar(produto, quantidade, conexao);
        }
        return dao.adicionarEstoque(produto.getProdId(), quantidade, conexao);
    }
}
