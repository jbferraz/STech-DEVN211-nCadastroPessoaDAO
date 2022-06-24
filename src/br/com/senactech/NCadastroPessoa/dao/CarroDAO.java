/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senactech.NCadastroPessoa.dao;
import br.com.senactech.NCadastroPessoa.conexao.Conexao;
import br.com.senactech.NCadastroPessoa.model.Carro;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jbferraz
 */
public class CarroDAO {
    
    public void cadastrarCarro(Carro cVO) throws SQLException{
        //Busca conexão do BD
        Connection con = Conexao.getConexao();
        //cria espaço de trabalho sql, é a área no Java onde 
        //vamos executar os scripts SQL
        Statement stat = con.createStatement();
        
        try {
            String sql;
            sql = "insert into carro values "
                    + "(null, '" 
                    + cVO.getPlaca()+ "', '"
                    + cVO.getMarca()+ "', '"
                    + cVO.getModelo()+ "', "
                    + cVO.getAnoFabricacao()+ ", "
                    + cVO.getAnoModelo()+ ", '"
                    + cVO.getCor()+ "', "
                    + cVO.getnPortas()+ ", "
                    + cVO.getIdPessoa()+ ");";
            //vamos executar no BD o SQl criado acima
            stat.execute(sql);
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir Carro! \n"
                + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<Carro> listarCarros() throws SQLException{
         //Busca conexão do BD
        Connection con = Conexao.getConexao();
        //cria espaço de trabalho sql, é a área no Java onde 
        //vamos executar os scripts SQL
        Statement stat = con.createStatement();
        
        try {
            String sql;
            sql = "select * from carro";
            ResultSet rs = stat.executeQuery(sql);
            ArrayList<Carro> carros = new ArrayList<>();
            while(rs.next()){
                Carro p = new Carro();
                //lado do java |x| lado do BD
                p.setIdCarro(rs.getInt("idCarro"));
                p.setPlaca(rs.getString("placa"));
                p.setMarca(rs.getString("marca"));
                p.setModelo(rs.getString("Modelo"));
                p.setAnoFabricacao(rs.getInt("anoFabricacao"));
                p.setAnoModelo(rs.getInt("anoModelo"));
                p.setCor(rs.getString("cor"));
                p.setnPortas(rs.getInt("nPortas"));
                p.setIdPessoa(rs.getInt("idPessoa"));
                //popula arrayList
                carros.add(p);
            }
            //retorna arrayList
            return carros;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar carros!\n" +
                    e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
    
    public boolean verPlaca(String placa) throws SQLException{
        //Busca conexão do BD
        Connection con = Conexao.getConexao();
        //cria espaço de trabalho sql, é a área no Java onde 
        //vamos executar os scripts SQL
        Statement stat = con.createStatement();
        
        boolean verPlaca = false;
        
        try {
            String sql;
            sql = "select placa from carro where placa = '" + placa + "'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {                
                verPlaca = rs.wasNull();
            }
        } catch (SQLException e) {
            throw new SQLException("Carro com esta Placa não existe! \n"
                + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
        return verPlaca;
    }
    
    public Carro getByDoc(String placa) throws SQLException{
        //Busca conexão do BD
        Connection con = Conexao.getConexao();
        //cria espaço de trabalho sql, é a área no Java onde 
        //vamos executar os scripts SQL
        Statement stat = con.createStatement();
        Carro c = new Carro();
        
        try {
            String sql;
            sql = "select * from carro where placa = '" + placa + "'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {                
                //lado do java |x| lado do banco
                c.setIdCarro(rs.getInt("idCarro"));
                c.setPlaca(rs.getString("placa"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setAnoFabricacao(rs.getInt("anoFabricacao"));
                c.setAnoModelo(rs.getInt("anoModelo"));
                c.setCor(rs.getString("cor"));
                c.setnPortas(rs.getInt("nPortas"));
                c.setIdPessoa(rs.getInt("idPessoa"));
            }
        } catch (SQLException e) {
            throw new SQLException("Carro com esta placa não existe!\n"
                + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
        return c;
    }
    
    public void deletarCarro(int id) throws SQLException{
        //Busca conexão do BD
        Connection con = Conexao.getConexao();
        //cria espaço de trabalho sql, é a área no Java onde 
        //vamos executar os scripts SQL
        Statement stat = con.createStatement();
        
        try {
            String sql;
            sql = "delete from carro where idCarro = " + id;
            stat.execute(sql);
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar Carro. \n"
                + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
    
    public void atualizarCarro (Carro cVO) throws SQLException{
        //Busca conexão do BD
        Connection con = Conexao.getConexao();
        //cria espaço de trabalho sql, é a área no Java onde 
        //vamos executar os scripts SQL
        Statement stat = con.createStatement();
        
        try {
            String sql;
            sql = "update carro set "
                    + "placa = '" + cVO.getPlaca()+ "', "
                    + "marca = '" + cVO.getMarca()+ "', "
                    + "modelo = '" + cVO.getModelo()+ "', "
                    + "anoFabricacao = " + cVO.getAnoFabricacao()+ ", "
                    + "anoModelo = " + cVO.getAnoModelo()+ ", "
                    + "cor = '" + cVO.getCor()+ "', "
                    + "nPortas = " + cVO.getnPortas()+ ", "
                    + "idPessoa = " + cVO.getIdPessoa()+ " "
                    + "where idCarro = " + cVO.getIdCarro();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar Carro. \n"
                +e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
}
