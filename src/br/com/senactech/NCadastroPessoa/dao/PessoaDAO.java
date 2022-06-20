/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senactech.NCadastroPessoa.dao;
import br.com.senactech.NCadastroPessoa.conexao.Conexao;
import br.com.senactech.NCadastroPessoa.model.Pessoa;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jbferraz
 */
public class PessoaDAO {
    
    public void cadastrarPessoa(Pessoa pVO) throws SQLException{
        //Busca conexão do BD
        Connection con = Conexao.getConexao();
        //cria espaço de trabalho sql, é a área no Java onde 
        //vamos executar os scripts SQL
        Statement stat = con.createStatement();
        
        try {
            String sql;
            sql = "insert into pessoa values "
                    + "(null, '" 
                    + pVO.getNomePessoa() + "', '"
                    + pVO.getCpf() + "', '"
                    + pVO.getEndereco() + "', '"
                    + pVO.getTelefone() + "', "
                    + pVO.getIdade() + ", "
                    + pVO.isStatus() + ");";
            //vamos executar no BD o SQl criado acima
            stat.execute(sql);
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir Pessoa! \n"
                + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<Pessoa> listarPessoas() throws SQLException{
         //Busca conexão do BD
        Connection con = Conexao.getConexao();
        //cria espaço de trabalho sql, é a área no Java onde 
        //vamos executar os scripts SQL
        Statement stat = con.createStatement();
        
        try {
            String sql;
            sql = "select * from pessoa";
            ResultSet rs = stat.executeQuery(sql);
            ArrayList<Pessoa> pessoas = new ArrayList<>();
            while(rs.next()){
                Pessoa p = new Pessoa();
                //lado do java |x| lado do BD
                p.setIdPessoa(rs.getInt("idPessoa"));
                p.setNomePessoa(rs.getString("nomePessoa"));
                p.setCpf(rs.getString("cpf"));
                p.setEndereco(rs.getString("endereco"));
                p.setTelefone(rs.getString("telefone"));
                p.setIdade(rs.getInt("idade"));
                p.setStatus(rs.getBoolean("status"));
                //popula arrayList
                pessoas.add(p);
            }
            //retorna arrayList
            return pessoas;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar pessoas!\n" +
                    e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
}
