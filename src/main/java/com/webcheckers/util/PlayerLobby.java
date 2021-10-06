package com.webcheckers.util;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerLobby {
    List<String> NamesInUse = new ArrayList<>(); //
    private final String NAME_TAKEN = "This name can't be used because this name has already been taken. Try again with a new name.";
    private final String NAME_INVALID = "This name is invalid, make sure to use only alphanumeric characters.";

    public PlayerLobby(){}
    /**
     * @return String error message if check fails and name is not added, return "Success" if done
     */
    public String checkAndAddName(String Name){
        //Checks
        if (! this.isNameValid(Name)) //if the name fails validity check, return false
            return "Invalid";
        if (! this.isNameUnique(Name)) //if the name fails uniqueness check, return false
            return "Taken";
        //if we get here, name valid
        //addition
        NamesInUse.add(Name);
        new Player(Name);
        return "Success";
    }

    // TODO: 10/2/21 remove a name after sign out (not in sprint 1)

    private boolean isNameValid(String Name){
        if (!this.containsAlphanumeric(Name) ) //if name does NOT contain alphanumeric chars, return false,
            return false;
        return true;
    }

    private boolean containsAlphanumeric(String str)
    {
        char[] charArray = str.toCharArray();
        for(char c:charArray)
        {
            if (Character.isLetterOrDigit(c))
                return true;
        }
        return false;
    }

    private boolean isNameUnique(String Name){
        if (NamesInUse.contains(Name))
            return false;
        return true;
    }

    public int numberOfPlayers(){
        return NamesInUse.size();
    }

}
