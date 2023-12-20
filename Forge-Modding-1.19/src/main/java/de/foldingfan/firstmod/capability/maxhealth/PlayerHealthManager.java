package de.foldingfan.firstmod.capability.maxhealth;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerHealthManager {
    // Erstelle eine Map zur Speicherung der Lebenspunkte der Spieler
    private Map<String, Integer> playerHealthMap;

    public PlayerHealthManager() {
// Initialisiere die Map beim Erstellen des <link>PlayerHealthManager</link>-Objekts
        playerHealthMap = new HashMap<>();
    }

    // Methode zum Hinzufügen eines Spielers und seiner Lebenspunkte zur Map
    public void addPlayer(Player player, int healthPoints) {
        playerHealthMap.put("" + player, healthPoints);
    }

    // Methode zum Aktualisieren der Lebenspunkte eines Spielers in der Map
    public void updatePlayerHealth(String playerId, int newHealthPoints) {
        playerHealthMap.put(playerId, newHealthPoints);
    }

    // Methode zum Abrufen der aktuellen Lebenspunkte eines Spielers aus der Map
    public int getPlayerHealth(String playerId) {
        return playerHealthMap.getOrDefault(playerId, 0); // Falls der Spieler nicht gefunden wird, wird 0 zurückgegeben
    }
}