package br.com.fiap.dao;

import br.com.fiap.to.RemedioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RemedioDAO extends Repository {
    public ArrayList<RemedioTO> findAll() {
        ArrayList<RemedioTO> remedios = new ArrayList<RemedioTO>();
        String sql = "select * from ddd_remedios order by codigo";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while(rs.next()){
                    RemedioTO remedio = new RemedioTO();
                    remedio.setCodigo(rs.getLong("codigo"));
                    remedio.setNome(rs.getString("nome"));
                    remedio.setPreco(rs.getDouble("preco"));
                    remedio.setDataDeFabricacao(rs.getDate("data_de_fabricacao").toLocalDate());
                    remedio.setDataDeValidade(rs.getDate("data_de_validade").toLocalDate());
                    remedios.add(remedio);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return remedios;
    }

    public RemedioTO findByCodigo(Long codigo){
        RemedioTO remedio = new RemedioTO();
        String sql = "select * from ddd_remedios where codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                remedio.setCodigo(rs.getLong("codigo"));
                remedio.setNome(rs.getString("nome"));
                remedio.setPreco(rs.getDouble("preco"));
                remedio.setDataDeFabricacao(rs.getDate("data_de_fabricacao").toLocalDate());
                remedio.setDataDeValidade(rs.getDate("data_de_validade").toLocalDate());
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return remedio;
    }

    public RemedioTO save(RemedioTO remedio) {
        String sql = "insert into ddd_remedios(codigo,nome,preco,data_de_fabricacao,data_de_validade) values(null,?,?,?,?)";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, remedio.getNome());
            ps.setDouble(2,remedio.getPreco());
            ps.setDate(3, Date.valueOf(remedio.getDataDeFabricacao()));
            ps.setDate(4, Date.valueOf(remedio.getDataDeValidade()));
            if (ps.executeUpdate() > 0) {
                return remedio;
            } else {
               return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());;
        } finally {
            closeConnection();
        }
        return null;
    }
}
