package dev.naimsulejmani.grupi1javafxregistration.repositories;

import dev.naimsulejmani.grupi1javafxregistration.infrastructure.DbConnection;
import dev.naimsulejmani.grupi1javafxregistration.infrastructure.Repository;
import dev.naimsulejmani.grupi1javafxregistration.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Repository<Person, Integer> {
    @Override
    public void add(Person model) {
        PreparedStatement statement = null;
        String sqlQyery = "INSERT INTO persons (Name, Surname, Birthdate, Passive ) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DbConnection.getInstance();
            statement = connection.prepareStatement(sqlQyery);
            statement.setString(1, model.getName());
            statement.setString(2, model.getSurname());
            statement.setDate(3, Date.valueOf(model.getBirthdate()));
            statement.setBoolean(4, model.isPassive());

            int rowsAffected = statement.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);
            //return rowsAffected>=0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modify(Integer id, Person model) {
        PreparedStatement statement = null;
        String sqlQyery = """
                            UPDATE persons
                            SET Name = ?, Surname = ?, Birthdate = ?, Passive = ?
                            WHERE Id = ?
                        """;
        try {
            Connection connection = DbConnection.getInstance();
            statement = connection.prepareStatement(sqlQyery);
            statement.setString(1, model.getName());
            statement.setString(2, model.getSurname());
            statement.setDate(3, Date.valueOf(model.getBirthdate()));
            statement.setBoolean(4, model.isPassive());
            statement.setInt(5, id);

            int rowsAffected = statement.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);
            //return rowsAffected>=0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeById(Integer id) {
        PreparedStatement statement = null;
        String sqlQyery = "DELETE FROM persons WHERE Id = ?";
        try {
            Connection connection = DbConnection.getInstance();
            statement = connection.prepareStatement(sqlQyery);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);
            //return rowsAffected>=0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = null;
        PreparedStatement statement = null;

        try {
            Connection connection = DbConnection.getInstance();
            statement = connection.prepareStatement("SELECT * FROM persons");
            ResultSet resultSet = statement.executeQuery();
            persons = new ArrayList<>();
            while (resultSet.next()) {
                Person person = toObject(resultSet);
                persons.add(person);
            }
        } catch (SQLException ex) {
            return null;
        }

        return persons;
    }

    private Person toObject(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("Id"));
        person.setName(resultSet.getString("Name"));
        person.setSurname(resultSet.getString("Surname"));

        //nje teknike se qysh me i menaxhu nullable prej databazes
        if (resultSet.getObject("Birthdate") != null)
            person.setBirthdate(resultSet.getDate("Birthdate").toLocalDate());
        person.setPassive(resultSet.getBoolean("Passive"));
        return person;
    }

    @Override
    public Person findById(Integer id) {
        PreparedStatement statement = null;
        Person person = null;
        try {
            Connection connection = DbConnection.getInstance();
            String query = "SELECT * FROM persons WHERE Id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                person = toObject(resultSet);
            }
        } catch (SQLException ex) {
            return null;
        }
        return person;
    }
}
