package learn.solar.ui;

import learn.solar.models.Material;

import java.util.Scanner;



public class Console {

    Scanner scn = new Scanner(System.in);

    public String getString(String prompt){
        System.out.print(prompt);
        return scn.nextLine();
    }

    public String getRequiredString(String prompt){
        String userInput = getString( prompt );
        while( userInput .isBlank()){
            userInput = getString(prompt);
        }
        return userInput;
    }

    public int getInt(String prompt){
        int toReturn = Integer.MIN_VALUE;

        boolean validInput = false;
        while(!validInput) {
            String userInput = getRequiredString(prompt);
            try {
                toReturn = Integer.parseInt(userInput);
                validInput = true;
            } catch (NumberFormatException ex) {

            }
        }
        return toReturn;
    }

    public int getInt( String prompt, int incMin, int incMax ){
        int toReturn = Integer.MIN_VALUE;
        boolean validInput = false;
        while( !validInput ){
            toReturn = getInt(prompt);
            if( incMin <= toReturn && toReturn <= incMax ){
                validInput = true;
            }
            //could be shortened to validInput = incMin <= toReturn && toReturn <= incMax
        }

        return toReturn;
    }

    public String editRequiredString( String prompt, String originalValue ){//AKA read required string
        //get a string from the user
        //but if they just press enter, output the original string

        String response = getString(prompt);
        if( response.isBlank() ){
            response = originalValue;
        }

        return response;
    }

    public int editInt(String prompt, int incMin, int incMax, int originalVal) {
        prompt = prompt + " (" + originalVal + "): ";
        int toReturn = Integer.MIN_VALUE;
        boolean validInput = false;
        while( !validInput ){
            String userInput = getString(prompt);
            if(userInput.isEmpty()) return originalVal;
            try{
                toReturn = Integer.parseInt(userInput);
                if( toReturn >= incMin && toReturn <= incMax){
                    validInput = true;
                }
            } catch (NumberFormatException ex){
                //do nothing
            }
        }

        return toReturn;
    }

    public boolean editBoolean(String prompt, boolean originalVal) {
        prompt = prompt + " (" + (originalVal ? "y" : "n") + "): ";

        String userInput = getString(prompt);
        if( userInput.isEmpty() ) return originalVal;

        return userInput.toLowerCase().trim().startsWith("y");
    }

    public Material getMaterialType(){
        System.out.println("Materials:");
        Material[] values = Material.values();

        for(int i = 0; i < values.length; i++){
            System.out.printf("%s. %s%n", i, values[i]);
        }
        int index = getInt("Select [0-4]: ", 0,4);
        return values[index];
    }

    public boolean getBoolean(String prompt){
        return getString(prompt).equals("y");
    }

    public void println(String line) {
        print( line + "\n");
    }

    private void print(String toPrint) {
        System.out.print(toPrint);
    }


    public void printHeader(String choose_material) {
        System.out.println();
        System.out.println(choose_material);
        System.out.println("=".repeat(choose_material.length()));
    }
}
