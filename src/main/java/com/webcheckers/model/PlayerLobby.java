package com.webcheckers.model;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class PlayerLobby {
    public List<String> NamesInUse; //
    public List<Player> Players;
    private final String NAME_TAKEN = "This name can't be used because this name has already been taken. Try again with a new name.";
    private final String NAME_INVALID = "This name is invalid, make sure to use only alphanumeric characters.";

    public PlayerLobby() {
        NamesInUse = new ArrayList<>();
        Players = new ArrayList<>();
    }

    /**
     * @return String error message if check fails and name is not added, return "Success" if done
     */
    public String checkAndAddName(String Name, String password) {
        //Checks
        if (!this.isNameValid(Name)) //if the name fails validity check, return false
            return "Invalid";
        if (!this.isNameUnique(Name)) //if the name fails uniqueness check, return false
            return "Taken";
        //if we get here, name valid
        //addition
        NamesInUse.add(Name);
        Players.add(new Player(Name));
        return "Success";

    }



    public void removeName(String Name)
    {
        NamesInUse.remove(Name);
        for(int i =0; i<Players.size(); i++)
        {
            if (Name.equals(Players.get(i).getName()))
            {
                Players.remove(i);
                return;
            }
        }
    }

    private boolean isNameValid(String Name) {
        if (!this.containsAlphanumeric(Name)) //if name does NOT contain alphanumeric chars, return false,
            return false;
        return true;
    }

    private boolean containsAlphanumeric(String str) {
        return (str != "") && (str.matches("^[a-zA-Z0-9]*$")); // "^" - beginning of line | "*" - matches zero or more occurrences | "$" - end of the line
    }

    private boolean isNameUnique(String Name) {
        if (NamesInUse.contains(Name))
            return false;
        return true;
    }

    public int numberOfPlayers() {
        return NamesInUse.size();
    }


    public Iterator<Player> iterator() {
        return Players.iterator();
    }
}