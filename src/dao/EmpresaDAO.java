package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // Importação da classe ResultSet
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Empresa; // Importação correta
import util.DataBaseConnector; // Importação correta
public class EmpresaDAO {
    private Connection connection;

    public EmpresaDAO() {
        connection = DataBaseConnector.connect();
    }

    public void cadastrarEmpresa(Empresa empresa) {
        try {
            String sql = "INSERT INTO empresa (nome, cnpj, endereco, telefone) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, empresa.getNome());
                statement.setString(2, empresa.getCnpj());
                statement.setString(3, empresa.getEndereco());
                statement.setString(4, empresa.getTelefone());
                statement.executeUpdate();
                System.out.println("Empresa cadastrada com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarEmpresa(Empresa empresa) {
        try {
            String sql = "UPDATE empresa SET nome=?, cnpj=?, endereco=?, telefone=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, empresa.getNome());
                statement.setString(2, empresa.getCnpj());
                statement.setString(3, empresa.getEndereco());
                statement.setString(4, empresa.getTelefone());
                statement.setInt(5, empresa.getId()); // Suponha que o ID da empresa seja conhecido
                statement.executeUpdate();
                System.out.println("Empresa atualizada com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerEmpresa(int empresaId) {
        try {
            String sql = "DELETE FROM empresa WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, empresaId);
                statement.executeUpdate();
                System.out.println("Empresa removida com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Empresa consultarEmpresaPorId(int empresaId) {
        try {
            String sql = "SELECT * FROM empresa WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, empresaId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String endereco = resultSet.getString("endereco");
                String telefone = resultSet.getString("telefone");

                return new Empresa(id, nome, cnpj, endereco, telefone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se a empresa não for encontrada
    }

    public List <Empresa> listarEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM empresa";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String endereco = resultSet.getString("endereco");
                String telefone = resultSet.getString("telefone");

                Empresa empresa = new Empresa(id, nome, cnpj, endereco, telefone);
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresas;
    }
    public void atualizarEmpresa(Empresa empresa) {
        try {
            String sql = "UPDATE empresa SET nome=?, cnpj=?, endereco=?, telefone=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, empresa.getNome());
            statement.setString(2, empresa.getCnpj());
            statement.setString(3, empresa.getEndereco());
            statement.setString(4, empresa.getTelefone());
            statement.setInt(5, empresa.getId()); // Suponha que o ID da empresa seja conhecido
            statement.executeUpdate();
            System.out.println("Empresa atualizada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
