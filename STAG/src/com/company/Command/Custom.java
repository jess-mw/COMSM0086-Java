package com.company.Command;

import com.company.Action;
import com.company.StagExceptions.InvalidCommand;
import com.company.StagExceptions.StagException;
import com.company.StagExceptions.SubjectDoesNotExist;
import com.company.Element.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Custom implements Command{

    private final String command;
    private final ArrayList<Action> actions;
    private Player player;
    private final ArrayList<Element> locations;
    private final HashMap<String, Player> players;

    public Custom(String command, ArrayList<Action> actions,
                  ArrayList<Element> locations, HashMap<String, Player> players){
        this.command = command;
        this.actions = actions;
        this.locations = locations;
        this.players = players;
    }

    @Override
    public String run(Player player) throws StagException {
        this.player = player;
        //loop through all of the actions we have read in from file
        for(Action a : actions){
            //get the action's triggers
            ArrayList<String> triggers = a.getTriggers();
            //if the command matches a trigger word of the action, check subjects,
            // consume, produce, then return narration
            String message = runTriggers(triggers, a);
            if(message!=null){
                return message;
            }
        }
        //if we get through all the actions and haven't found it, invalid command
        throw new InvalidCommand(command);
    }

    private String runTriggers(ArrayList<String> triggers, Action a) throws SubjectDoesNotExist {
        for(String t : triggers) {
            if (command.contains(t)) {
                //check all subjects exist in command and in game
                checkCommand(command, a);
                //check if anything to consume, if there is, remove from location/inventory
                Consume consume = new Consume(a, locations, players);
                String message = consume.run(player);
                //check if anything to produce, if there is, add to location
                Produce produce = new Produce(a, locations);
                produce.run(player);
                return getMessage(a, message);
            }
        } return null;
    }

    private String getMessage(Action a, String message){
        //return the action's narration - with message if health ran out
        if(message!=null){
            return a.getNarration()+"\n"+message;
        }
        return a.getNarration();
    }

    private void checkCommand(String command, Action action) throws SubjectDoesNotExist {
        //need to check that at least one subject is present in the command (if required)
        if(action.getSubjects()==null) {
            throw new SubjectDoesNotExist();
        }
        if(!checkCommandSubjects(command, action)) {
            throw new SubjectDoesNotExist();
        }
    }

    private boolean checkCommandSubjects(String command, Action a){
        ArrayList<String> subjects = a.getSubjects();
        for(String s : subjects){
            if(command.contains(s)){
                return true;
            }
        }
        return false;
    }
}
