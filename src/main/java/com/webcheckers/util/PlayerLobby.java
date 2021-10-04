package com.webcheckers.util;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerLobby {
    List<String> NamesInUse = new ArrayList<>(); //

        public PlayerLobby(){}
    /**
     * @return String error message if check fails and name is not added, return "Success" if done
     */
    public String checkAndAddName(String Name){
        //Checks
        if (! isNameValid(Name)) //if the name fails validity check, return false
            return "The name '"+Name+"' is invalid, please insure it contains at least one alphanumeric character, please enter a new name";
        if (! this.isNameUnique(Name)) //if the name fails uniqueness check, return false
            return "The name '"+Name+"' is already taken, please enter a new name";
        //if we get here, name valid
        //addition
        NamesInUse.add(Name);
        new Player(Name);
        return "Success";
    }

    // TODO: 10/2/21 remove a name after sign out (not in sprint 1)

    public boolean isNameValid(String Name){
        if (!this.containsAlphanumeric(Name) ) //if name does NOT contain alphanumeric chars, return false,
            return false;
        return true;
    }

    public boolean containsAlphanumeric(String str)
    {
        char[] charArray = str.toCharArray();
        for(char c:charArray)
        {
            if (Character.isLetterOrDigit(c))
                return true;
        }
        return false;
    }

    public boolean isNameUnique(String Name){
        if (NamesInUse.contains(Name))
            return false;
        return true;
    }

}
