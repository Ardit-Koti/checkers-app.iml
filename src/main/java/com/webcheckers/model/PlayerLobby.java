package com.webcheckers.model;

import com.webcheckers.model.Player;

import java.util.*;

public class PlayerLobby {
    private final List<String> NamesInUse; //
    private final List<Player> Players;
    private final Map<String, String> passcodes;

    public PlayerLobby() {
        NamesInUse = new ArrayList<>();
        Players = new ArrayList<>();
        passcodes = new HashMap<>();
    }

    /**
     * @return String error message if check fails and name is not added, return "Success" if done
     */
    public String checkAndAddName(String Name, String password) {
        //Checks
        if (Name.equals("") && password.equals("")) {
            return "NothingAtAll";
        }
        if (!this.isNameValid(Name)) //if the name fails validity check, return false
            return "Invalid";
        if (!this.isNameUnique(Name)) //if the name fails uniqueness check, return false
            return "Taken";
        if(password.equals("")) {
            return "Nothing";
        }
        if(!passcodes.containsKey(Name))
            passcodes.put(Name,password);
        else if(passcodes.containsKey(Name) && !password.equals(passcodes.get(Name)))
            return "BadCode";



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
        //if name does NOT contain alphanumeric chars, return false,
        return this.containsAlphanumeric(Name);
    }

    private boolean containsAlphanumeric(String str) {
        return (!str.equals("")) && (str.matches("^[a-zA-Z0-9]*$")); // "^" - beginning of line | "*" - matches zero or more occurrences | "$" - end of the line
    }

    private boolean isNameUnique(String Name) {
        return !NamesInUse.contains(Name);
    }

    public int numberOfPlayers() {
        return NamesInUse.size();
    }
    public List<String> getNamesInUse() {return this.NamesInUse;}

    public Player getPlayer(String name)
    {
        for(int i =0; i<Players.size(); i++)
        {
            if(Players.get(i).getName().equals(name))
            {
                return Players.get(i);
            }
        }
        return null;
    }


    public Iterator<Player> iterator() {
        return Players.iterator();
    }
}