package learn.solar.ui;

import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.sql.SQLOutput;
import java.util.List;
public class View {

    Console console = new Console();

    public MainMenuChoice displayMenu() throws InvalidMenuChoiceException{
        System.out.println("\nMain Menu");
        System.out.println("=".repeat("Main Menu".length()));


        System.out.println("0. Exit");
        System.out.println("1. Add a Panel");
        System.out.println("2. Find Panels by Section");
        System.out.println("3. Update a panel");
        System.out.println("4. Delete a panel");

        int userChoice = console.getInt("Select [0-4]", 0,4);

        switch (userChoice){
            case 0:
                return MainMenuChoice.QUIT;
            case 1:
                return MainMenuChoice.CREATE_PANEL;
            case 2:
                return MainMenuChoice.DISPLAY_PANEL_BY_SECTION;
            case 3:
                return MainMenuChoice.EDIT_PANEL;
            case 4:
                return MainMenuChoice.DELETE_PANEL;
            default:
                throw new InvalidMenuChoiceException("Invalid input");
        }
    }

    public Panel getNewPanelDetails() {
        /*
            String section;
            private int row;
            private int column;
            private Material panelMaterial;
            private int yearInstalled;
            private boolean isTracking;
         */
        Panel toBuild = new Panel();

        String section = console.getRequiredString("Section: ");
        int row = console.getInt("Row: ",1, 250);
        int column = console.getInt("Column: ", 1 ,250);
        Material material = console.getMaterialType();
        int year = console.getInt("Installation Year: ", 1884,2021);
        boolean tracking = console.getBoolean("Tracked [y/n]: ");

        toBuild.setSection(section);
        toBuild.setRow(row);
        toBuild.setColumn(column);
        toBuild.setPanelMaterial(material);
        toBuild.setYearInstalled(year);
        toBuild.setTracking(tracking);

        return toBuild;
    }

    public void displayPanel(Panel fullyHydrated) {
        System.out.println("[Success]");
        System.out.println("Panel " + fullyHydrated.getSection() + "-" + fullyHydrated.getRow() + "-" + fullyHydrated.getColumn() + " added.");
    }

    public void displayError(String message) {
        System.out.println("[ERROR]");
    }


    public void printHeader (String message){
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public String readPanelSectionType() {

        String section = console.getRequiredString("Section: ");

        return section;
    }
    
    public void displayPanelsBySection(List<Panel> panels){
        printHeader("Panels: ");
        if(panels.size() == 0){
            System.out.println("No Panels found. ");
        } else{
            System.out.printf("%s %s %s %s %s %n", "Row", "Col","Year","Material","Tracking");
            for(Panel p : panels){
                System.out.printf("%s %s %s %s %s%n",
                                p.getRow(),
                                p.getColumn(),
                                p.getYearInstalled(),
                                p.getPanelMaterial(),
                                p.isTracking());
            }
        }
    }

    public Panel editPanel(Panel toUpdate) {

        //Editing Treeline-10-5
        //Press [Enter] to keep original value.

        console.println("Editing " + toUpdate.getSection() + "-" + toUpdate.getRow() + "-" + toUpdate.getColumn());
        console.println("Press [Enter] to keep original value.");

        toUpdate.setSection( console.editRequiredString( "Section", toUpdate.getSection() ) );
        toUpdate.setRow( console.editInt( "Row", 1, 250, toUpdate.getRow()));
        toUpdate.setColumn( console.editInt( "Col", 1, 250, toUpdate.getColumn()));
        toUpdate.setYearInstalled( console.editInt( "Installation Year", Integer.MIN_VALUE, 2022, toUpdate.getYearInstalled()));

        Material newMat = editMaterial( toUpdate.getPanelMaterial() );
        toUpdate.setPanelMaterial( newMat );

        toUpdate.setTracking( console.editBoolean( "Tracked", toUpdate.isTracking()));

        return toUpdate;
    }

    private Material editMaterial(Material original) {
        console.printHeader("Choose Material");
        for( int i = 0; i < Material.values().length; i++){
            console.println( (i+1) + ": " + Material.values()[i].getDisplayVal());
        }
        int userIndex = console.editInt("Please enter selection", 1, Material.values().length, original.ordinal() + 1) - 1;

        return Material.values()[userIndex];
    }

    public void emptySection() {
        displayError( "Section is empty." );
    }

    public Panel selectSectionPanel(String sectionName, List<Panel> sectionPanels) {
        console.printHeader(sectionName);
        for( int i = 0; i < sectionPanels.size(); i++){
            Panel toDisplay = sectionPanels.get(i);

            console.println( (i+1) +": [" + toDisplay.getRow() + ", " + toDisplay.getColumn() + "]");
        }

        int selectedIndex = console.getInt("Please choose Panel: ", 1, sectionPanels.size()) - 1;


        return sectionPanels.get(selectedIndex);
    }

    public Panel getPanel() {
        Panel toBuild = new Panel();

        String section = console.getRequiredString("Section: ");
        int row = console.getInt("Row: ",1, 250);
        int column = console.getInt("Column: ", 1 ,250);

        toBuild.setSection(section);
        toBuild.setRow(row);
        toBuild.setColumn(column);

        return toBuild;
    }

    public void updateSuccess(Panel payload) {
        console.println( payload.getSection() + "-" + payload.getRow() + "-" + payload.getColumn() + " successfully updated!");
    }

    public void printErrorMessage(List<String> messages) {
        throw new UnsupportedOperationException();
    }


}
