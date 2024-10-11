package br.com.fiap.bo;

import br.com.fiap.dao.RemedioDAO;
import br.com.fiap.to.RemedioTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class RemedioBO {
    private RemedioDAO remedioDAO;

    public ArrayList<RemedioTO> findAll(){
        remedioDAO = new RemedioDAO();
        //aqui que coloca a regra de negocio
        return remedioDAO.findAll();
    }

    public RemedioTO findByCodigo(Long codigo){
        remedioDAO = new RemedioDAO();
        //aqui emplementa a regra de negocio
        return remedioDAO.findByCodigo(codigo);
    }

    public RemedioTO save(RemedioTO remedio) {
        remedioDAO = new RemedioDAO();
        //aqui se emplementa a regra de negocios
///        if (!remedio.getDataDeFabricacao().isBefore(LocalDate.now())) {
///            return null;
///       }
///        if (!remedio.getDataDeValidade().isAfter(LocalDate.now())){
///            return null;
///        }
        return remedioDAO.save(remedio);
    }
}
