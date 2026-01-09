package org.example.rpgfinal.dao;

import org.example.rpgfinal.model.character.Character;
import org.example.rpgfinal.model.character.CharacterBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterDao implements Dao<Character> {

    private static final String DB_URL = "jdbc:sqlite:characters.db";

    public CharacterDao() {
        // Crée la table si elle n'existe pas
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = """
                    CREATE TABLE IF NOT EXISTS characters (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL UNIQUE,
                        characterClass TEXT,
                        hp INTEGER,
                        strength INTEGER,
                        intelligence INTEGER,
                        agility INTEGER
                    )
                    """;
            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'initialisation de la base", e);
        }
    }

    @Override
    public void save(Character character) {
        String sql = """
                INSERT OR REPLACE INTO characters
                (name, characterClass, hp, strength, intelligence, agility)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, character.getName());
            pstmt.setString(2, character.getCharacterClass());
            pstmt.setInt(3, character.getHp());
            pstmt.setInt(4, character.getStrength());
            pstmt.setInt(5, character.getIntelligence());
            pstmt.setInt(6, character.getAgility());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du personnage", e);
        }
    }

    @Override
    public List<Character> findAll() {
        List<Character> characters = new ArrayList<>();
        String sql = "SELECT name, characterClass, hp, strength, intelligence, agility FROM characters";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Character c = new CharacterBuilder()
                        .name(rs.getString("name"))
                        .characterClass(rs.getString("characterClass"))
                        .hp(rs.getInt("hp"))
                        .strength(rs.getInt("strength"))
                        .intelligence(rs.getInt("intelligence"))
                        .agility(rs.getInt("agility"))
                        .build();
                characters.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des personnages", e);
        }

        return characters;
    }

    @Override
    public void delete(Character character) {
        String sql = "DELETE FROM characters WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, character.getName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du personnage", e);
        }
    }
}