/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senactech.NCadastroPessoa.services;

import br.com.senactech.NCadastroPessoa.dao.CarroDAO;
import br.com.senactech.NCadastroPessoa.dao.DAOFactory;
import br.com.senactech.NCadastroPessoa.model.Carro;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jbferraz
 */
public class CarroServicos {

    public void cadCarro(Carro cVO) throws SQLException {
        CarroDAO pDAO = DAOFactory.getCarroDAO();
        pDAO.cadastrarCarro(cVO);
    }

    public ArrayList<Carro> getCarros() throws SQLException {
        CarroDAO pDAO = DAOFactory.getCarroDAO();
        return pDAO.listarCarros();
    }

    public boolean verPlacaBD(String placa) throws SQLException {
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        return cDAO.verPlaca(placa);
    }

    public Carro buscarCarro(String placa) throws SQLException {
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        return cDAO.getByDoc(placa);
    }
    
    public void deletarCarroBD(int id) throws SQLException{
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        cDAO.deletarCarro(id);
    }
    
    public void atualizarCarroBD(Carro cVO)throws SQLException{
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        cDAO.atualizarCarro(cVO);
    }
}
